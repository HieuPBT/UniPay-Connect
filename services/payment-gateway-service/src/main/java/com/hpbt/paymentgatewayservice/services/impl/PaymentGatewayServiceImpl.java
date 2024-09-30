package com.hpbt.paymentgatewayservice.services.impl;

import com.hpbt.paymentgatewayservice.clients.ZaloPayClientV2;
import com.hpbt.paymentgatewayservice.dto.requests.PaymentGatewayRequest;
import com.hpbt.paymentgatewayservice.dto.requests.zalopay.version2.ZaloPayCreateRequest;
import com.hpbt.paymentgatewayservice.dto.requests.zalopay.version2.ZaloPayQueryRefundRequest;
import com.hpbt.paymentgatewayservice.dto.requests.zalopay.version2.ZaloPayQueryRequest;
import com.hpbt.paymentgatewayservice.dto.requests.zalopay.version2.ZaloPayRefundRequest;
import com.hpbt.paymentgatewayservice.dto.responses.MoMoResponse;
import com.hpbt.paymentgatewayservice.dto.responses.ZalopayResponse;
import com.hpbt.paymentgatewayservice.repositories.PaymentGatewayRepository;
import com.hpbt.paymentgatewayservice.services.PaymentGatewayService;
import com.hpbt.paymentgatewayservice.utils.commons.CommonUtil;
import com.hpbt.paymentgatewayservice.utils.zalopay.crypto.HMACUtil;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Slf4j
public class PaymentGatewayServiceImpl implements PaymentGatewayService {
   final PaymentGatewayRepository paymentGatewayRepository;

   final ZaloPayClientV2 zaloPayClientV2;

    @Value("${zalopay.version1.key.appId}")
    int zalopayV1AppId;

    @Value("${zalopay.version1.key.key1}")
    String zalopayV1Key1;

    @Value("${zalopay.version1.key.key2}")
    String zaloPayV1Key2;

    @Value("${zalopay.version1.url.sandbox}")
    String zalopayV1Sandbox;

    @Value("${zalopay.version1.path.create}")
    String zalopayV1Create;

    @Value("${zalopay.version2.key.appId}")
    int zalopayV2AppId;

    @Value("${zalopay.version2.key.key1}")
    String zalopayV2Key1;

    @Value("${zalopay.version2.key.key2}")
    String zaloPayV2Key2;

    @Value("${zalopay.version2.url.sandbox}")
    String zalopayV2Sandbox;

    @Value("${zalopay.version2.path.create}")
    String zalopayV2Create;

    @Override
    public MoMoResponse createMoMo(PaymentGatewayRequest request) {
        return null;
    }

    @Override
    public Map<String, Object> zalopayCreateV2(ZaloPayCreateRequest request) {
        Map<String, Object> data = new HashMap<String, Object>(){
            {
                put("app_id", request.app_id());
                put("app_trans_id", CommonUtil.getCurrentTimeString("yyMMdd") + "_" + new Random().nextInt(1000000)); // mã giao dich có định dạng yyMMdd_xxxx
                put("app_time", System.currentTimeMillis()); // miliseconds
                put("app_user", request.app_user());
                put("amount", request.amount());
                put("description", request.description());
                put("bank_code", request.bank_code());
                put("item", request.item());
                put("embed_data", request.embed_data());
                put("callback_url", request.callbackUrl());
            }
        };


        String rawData = data.get("app_id") + "|" + data.get("app_trans_id") + "|" + data.get("app_user") + "|" + data.get("amount")
                + "|" + data.get("app_time") + "|" + data.get("embed_data") + "|" + data.get("item");

        data.put("mac", HMACUtil.HMacHexStringEncode(HMACUtil.HMACSHA256, request.accessKey(), rawData));

        System.out.println(data);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        for (Map.Entry<String, Object> entry : data.entrySet()) {
            map.add(entry.getKey(), String.valueOf(entry.getValue()));
        }

        ResponseEntity<String> response = zaloPayClientV2.createZalopayV2(map);
        JSONObject result = new JSONObject(response.getBody());

        return result.toMap();
    }

    @Override
    public Map<String, Object> zalopayQueryV2(ZaloPayQueryRequest request) {
        Map<String, Object> data = new HashMap<>(){
            {
                put("app_id", request.app_id());
                put("app_trans_id", request.app_trans_id());
            }
        };

        String rawDta = data.get("app_id") + "|" + data.get("app_trans_id") + "|" + request.accessKey();
        data.put("mac", HMACUtil.HMacHexStringEncode(HMACUtil.HMACSHA256, request.accessKey(), rawDta));

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        for (Map.Entry<String, Object> entry : data.entrySet()) {
            map.add(entry.getKey(), String.valueOf(entry.getValue()));
        }

        ResponseEntity<String> response = zaloPayClientV2.queryZalopayV2(map);
        JSONObject result = new JSONObject(response.getBody());

        return result.toMap();
    }

    @Override
    public Map<String, Object> zalopayRefundV2(ZaloPayRefundRequest request) {
        Random rand = new Random();
        String uid = System.currentTimeMillis() + "" + (111 + rand.nextInt(888)); // unique id
        Map<String, Object> data = new HashMap<>(){
            {
                put("m_refund_id", CommonUtil.getCurrentTimeString("yyMMdd") + "_" + request.app_id() + "_" + uid);
                put("app_id", request.app_id());
                put("zp_trans_id", request.zp_trans_id());
                put("amount", request.amount());
                put("description", request.description());
                put("timestamp", System.currentTimeMillis());
            }
        };

        String rawData;

        if(request.refund_fee_amount() != null){
            data.put("refund_fee_amount", request.refund_fee_amount());
            rawData = data.get("app_id") + "|" + data.get("zp_trans_id")
                    + "|" + data.get("amount") + "|" + data.get("refund_fee_amount")
                    + data.get("description") + "|" + data.get("timestamp");
        }

        rawData = data.get("app_id") + "|" + data.get("zp_trans_id")
                + "|" + data.get("amount") + "|" + data.get("description")
                + "|" + data.get("timestamp");

        System.out.println(rawData);

        data.put("mac", HMACUtil.HMacHexStringEncode(HMACUtil.HMACSHA256, request.accessKey(), rawData));


        System.out.println(data);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        for (Map.Entry<String, Object> entry : data.entrySet()) {
            map.add(entry.getKey(), String.valueOf(entry.getValue()));
        }

        ResponseEntity<String> response = zaloPayClientV2.refundZalopayV2(map);
        JSONObject result = new JSONObject(response.getBody());

        Map<String, Object> resultMap = result.toMap();
        resultMap.put("m_refund_id", data.get("m_refund_id"));

        return resultMap;
    }

    @Override
    public Map<String, Object> zalopayQueryRefundV2(ZaloPayQueryRefundRequest request) {
        Map<String, Object> data = new HashMap<>(){
            {
                put("m_refund_id", request.m_refund_id());
                put("app_id", request.app_id());
                put("timestamp", System.currentTimeMillis());
            }
        };

        String rawData = data.get("app_id") + "|" + data.get("m_refund_id") +"|" + data.get("timestamp");

        System.out.println(rawData);

        data.put("mac", HMACUtil.HMacHexStringEncode(HMACUtil.HMACSHA256, request.accessKey(), rawData));


        System.out.println(data);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        for (Map.Entry<String, Object> entry : data.entrySet()) {
            map.add(entry.getKey(), String.valueOf(entry.getValue()));
        }

        ResponseEntity<String> response = zaloPayClientV2.queryRefundZalopayV2(map);
        JSONObject result = new JSONObject(response.getBody());

        return result.toMap();
    }


}
