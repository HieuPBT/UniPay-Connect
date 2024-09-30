package com.hpbt.paymentgatewayservice.services.impl;

import com.hpbt.paymentgatewayservice.dto.requests.PaymentGatewayRequest;
import com.hpbt.paymentgatewayservice.dto.responses.ZalopayResponse;
import com.hpbt.paymentgatewayservice.services.ZaloPayService;
import com.hpbt.paymentgatewayservice.utils.commons.CommonUtil;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class ZaloPayServiceImpl implements ZaloPayService {
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
    public ZalopayResponse zalopayCreateV2(PaymentGatewayRequest request) {
        Map<String, Object> data = new HashMap<String, Object>(){
            {
                put("app_id", zalopayV2AppId);
                put("app_trans_id", CommonUtil.getCurrentTimeString("yyMMdd") + "_" + new Random().nextInt(1000000)); // mã giao dich có định dạng yyMMdd_xxxx
                put("app_time", System.currentTimeMillis()); // miliseconds
                put("app_user", "user123");
                put("amount", request.amount());
                put("description", request.description());
                put("bank_code", "zalopayapp");
                put("item", request.item());
                put("embed_data", request.embed_data());
                put("callback_url", request.callbackUrl());
            }
        };
    }
}
