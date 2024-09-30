package com.hpbt.paymentgatewayservice.controllers;

import com.google.gson.JsonObject;
import com.hpbt.paymentgatewayservice.clients.MoMoClient;
import com.hpbt.paymentgatewayservice.clients.ZaloPayClientV1;
import com.hpbt.paymentgatewayservice.dto.requests.MoMoRequest;
import com.hpbt.paymentgatewayservice.dto.requests.PaymentGatewayRequest;
import com.hpbt.paymentgatewayservice.dto.requests.ZaloPayRequest;
import com.hpbt.paymentgatewayservice.dto.responses.ApiResponse;
import com.hpbt.paymentgatewayservice.dto.responses.MoMoResponse;
import com.hpbt.paymentgatewayservice.dto.responses.ZalopayResponse;
import com.hpbt.paymentgatewayservice.services.PaymentGatewayService;
import com.hpbt.paymentgatewayservice.utils.commons.StatusCode;
import com.hpbt.paymentgatewayservice.utils.zalopay.crypto.HMACUtil;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@RestController
@RequestMapping("/api/v1/payment")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PaymentGatewayController {
    final PaymentGatewayService paymentGatewayService;

    final MoMoClient moMoClient;

    final ZaloPayClientV1 zaloPayClientV1;

    @Value("${momo.key.access-key}")
    String momoAccessKey;

    @Value("${momo.key.secret-key}")
    String momoSecretKey;

    @Value("${zalopay.version1.key.appId}")
    int zalopayV1AppId;

    @Value("${zalopay.version1..key.key1}")
    String zalopayV1Key1;

    @Value("${zalopay.version1.key.key2}")
    String zaloPayV2Key2;

    @Value("${zalopay.version1.url.sandbox}")
    String zalopayV1Sandbox;

    @Value("${zalopay.version1.path.create}")
    String zalopayV1Create;

    @PostMapping("/momo")
    public ResponseEntity<?> createMoMo() throws Exception {
        String accessKey = "F8BBA842ECF85";
        String secretKey = "K951B6PE1waDMi640xX08PD3vg6EkVlz";
        String partnerCode = "MOMOT5BZ20231213_TEST";
        String requestId = partnerCode + "_" + Instant.now().toEpochMilli();
        String orderId = requestId;
        String requestType = "captureWallet";
        String extraData = "";
        String orderInfo = "Pay";
        String lang = "en";

        long amount = 3000;
        String ipnUrl = "test";
        String redirectUrl = "";

        String rawData = String.format(
                "accessKey=%s&amount=%s&extraData=%s&ipnUrl=%s&orderId=%s&orderInfo=%s&partnerCode=%s&redirectUrl=%s&requestId=%s&requestType=%s",
                accessKey, amount, extraData, ipnUrl, orderId, orderInfo, partnerCode, redirectUrl, requestId, requestType
        );

        System.out.println(rawData);
        String signature = generateHmacSHA256(rawData, secretKey);
        System.out.println(signature);

        MoMoRequest moMoRequest = new MoMoRequest(
                partnerCode,
                requestId,
                amount,
                orderId,
                ipnUrl,
                redirectUrl,
                orderInfo,
                requestType,
                extraData,
                lang,
                signature

        );

        MoMoResponse moMoResponse = moMoClient.createMoMo(moMoRequest);

        return ResponseEntity.ok(moMoResponse);
    }

    private String generateHmacSHA256(String data, String key) throws Exception {
        Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
        SecretKeySpec secret_key = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
        sha256_HMAC.init(secret_key);
        return Base64.getEncoder().encodeToString(sha256_HMAC.doFinal(data.getBytes(StandardCharsets.UTF_8)));
    }

    @PostMapping("/zalopay/v1/create")
    public ResponseEntity<?> createZaloPayV1(@RequestBody @Valid PaymentGatewayRequest request) {
        final Map embeddata = new HashMap() {
            {
                put("redirecturl", request.redirectUrl());
            }
        };

        final Map[] item = {
                new HashMap() {

                }
        };

        ApiResponse apiResponse = new ApiResponse();

        Map<String, Object> order = new HashMap<String, Object>() {
            {
                put("appid", zalopayV1AppId);
                put("apptransid", getCurrentTimeString("yyMMdd") + "_" + new Random().nextInt(100000)); // mã giao dich có định dạng yyMMdd_xxxx
                put("apptime", System.currentTimeMillis()); // miliseconds
                put("appuser", "demo");
                put("amount", request.amount());
                put("description", "Export PDF Fee - " + request.amount());
                put("bankcode", "zalopayapp");
                put("item", new JSONObject(item).toString());
                put("embeddata", new JSONObject(embeddata).toString());
            }
        };

        String data = order.get("appid") + "|" + order.get("apptransid") + "|" + order.get("appuser") + "|" + order.get("amount")
                + "|" + order.get("apptime") + "|" + order.get("embeddata") + "|" + order.get("item");

        order.put("mac", HMACUtil.HMacHexStringEncode(HMACUtil.HMACSHA256, zalopayV1Key1, data));

        System.out.println(order);

//        RestTemplate restTemplate = new RestTemplate();
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//
//        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
//        for (Map.Entry<String, Object> entry : order.entrySet()) {
//            map.add(entry.getKey(), String.valueOf(entry.getValue()));
//        }
//
//        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(map, headers);
//
//        ResponseEntity<String> response = restTemplate.postForEntity(zalopayV1Sandbox + zalopayV1Create, requestEntity, String.class);


        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        for (Map.Entry<String, Object> entry : order.entrySet()) {
            map.add(entry.getKey(), String.valueOf(entry.getValue()));
        }

        ResponseEntity<String> response = zaloPayClientV1.createZalopayV1(map);

        JSONObject result = new JSONObject(response.getBody());

        apiResponse.setCode(StatusCode.SUCCESS.getCode());
        apiResponse.setMessage(StatusCode.SUCCESS.getMessage());
        apiResponse.setResult(result.toMap());

        System.out.println(result);

        return ResponseEntity.ok(apiResponse);

    }

    public static String getCurrentTimeString(String format) {
        Calendar cal = new GregorianCalendar(TimeZone.getTimeZone("GMT+7"));
        SimpleDateFormat fmt = new SimpleDateFormat(format);
        fmt.setCalendar(cal);
        return fmt.format(cal.getTimeInMillis());
    }
}
