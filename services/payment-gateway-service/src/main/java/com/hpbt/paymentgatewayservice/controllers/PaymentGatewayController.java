package com.hpbt.paymentgatewayservice.controllers;

import com.hpbt.paymentgatewayservice.clients.MoMoClient;
import com.hpbt.paymentgatewayservice.clients.ZaloPayClientV1;
import com.hpbt.paymentgatewayservice.clients.ZaloPayClientV2;
import com.hpbt.paymentgatewayservice.dto.requests.MoMoRequest;
import com.hpbt.paymentgatewayservice.dto.requests.PaymentGatewayRequest;
import com.hpbt.paymentgatewayservice.dto.requests.momo.MoMoCreateRequest;
import com.hpbt.paymentgatewayservice.dto.requests.zalopay.version2.ZaloPayCreateRequest;
import com.hpbt.paymentgatewayservice.dto.requests.zalopay.version2.ZaloPayQueryRefundRequest;
import com.hpbt.paymentgatewayservice.dto.requests.zalopay.version2.ZaloPayQueryRequest;
import com.hpbt.paymentgatewayservice.dto.requests.zalopay.version2.ZaloPayRefundRequest;
import com.hpbt.paymentgatewayservice.dto.responses.ApiResponse;
import com.hpbt.paymentgatewayservice.dto.responses.MoMoResponse;
import com.hpbt.paymentgatewayservice.services.PaymentGatewayService;
import com.hpbt.paymentgatewayservice.utils.commons.CommonUtil;
import com.hpbt.paymentgatewayservice.utils.commons.StatusCode;
import com.hpbt.paymentgatewayservice.utils.zalopay.crypto.HMACUtil;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.*;

@RestController
@RequestMapping("/api/v1/payment")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PaymentGatewayController {
    final PaymentGatewayService paymentGatewayService;

    final MoMoClient moMoClient;

    final ZaloPayClientV1 zaloPayClientV1;

    final ZaloPayClientV2 zaloPayClientV2;

    @Value("${momo.key.access-key}")
    String momoAccessKey;

    @Value("${momo.key.secret-key}")
    String momoSecretKey;

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

//    @PostMapping("/momo")
//    public ResponseEntity<?> createMoMo() throws Exception {
//        String accessKey = "F8BBA842ECF85";
//        String secretKey = "K951B6PE1waDMi640xX08PD3vg6EkVlz";
//        String partnerCode = "MOMOT5BZ20231213_TEST";
//        String requestId = partnerCode + "_" + Instant.now().toEpochMilli();
//        String orderId = requestId;
//        String requestType = "captureWallet";
//        String extraData = "";
//        String orderInfo = "Pay";
//        String lang = "en";
//
//        long amount = 3000;
//        String ipnUrl = "test";
//        String redirectUrl = "";
//
//        String rawData = String.format(
//                "accessKey=%s&amount=%s&extraData=%s&ipnUrl=%s&orderId=%s&orderInfo=%s&partnerCode=%s&redirectUrl=%s&requestId=%s&requestType=%s",
//                accessKey, amount, extraData, ipnUrl, orderId, orderInfo, partnerCode, redirectUrl, requestId, requestType
//        );
//
//        System.out.println(rawData);
//        String signature = generateHmacSHA256(rawData, secretKey);
//        System.out.println(signature);
//
//        MoMoRequest moMoRequest = new MoMoRequest(
//                partnerCode,
//                requestId,
//                amount,
//                orderId,
//                ipnUrl,
//                redirectUrl,
//                orderInfo,
//                requestType,
//                extraData,
//                lang,
//                signature
//
//        );
//
//        MoMoResponse moMoResponse = moMoClient.createMoMo(moMoRequest);
//
//        return ResponseEntity.ok(moMoResponse);
//    }

    @PostMapping("/momo/create")
    public ResponseEntity<ApiResponse> createMoMo(@RequestBody @Valid MoMoCreateRequest request) {
        ApiResponse apiResponse = new ApiResponse();

        apiResponse.setCode(StatusCode.SUCCESS.getCode());
        apiResponse.setMessage(StatusCode.SUCCESS.getMessage());

        apiResponse.setResult(paymentGatewayService.createMoMo(request));

        return ResponseEntity.ok(apiResponse);
    }

    private String generateHmacSHA256(String data, String key) throws Exception {
        Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
        SecretKeySpec secret_key = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
        sha256_HMAC.init(secret_key);
        return Base64.getEncoder().encodeToString(sha256_HMAC.doFinal(data.getBytes(StandardCharsets.UTF_8)));
    }

    @PostMapping("/zalopay/v2/create")
    public ResponseEntity<ApiResponse> createZaloPayV2(@RequestBody @Valid ZaloPayCreateRequest request) {
        ApiResponse apiResponse = new ApiResponse();

        apiResponse.setCode(StatusCode.SUCCESS.getCode());
        apiResponse.setMessage(StatusCode.SUCCESS.getMessage());
        apiResponse.setResult(paymentGatewayService.zalopayCreateV2(request));

        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("/zalopay/v2/query")
    public ResponseEntity<ApiResponse> queryZaloPayV2(@RequestBody @Valid ZaloPayQueryRequest request) {
        ApiResponse apiResponse = new ApiResponse();

        apiResponse.setCode(StatusCode.SUCCESS.getCode());
        apiResponse.setMessage(StatusCode.SUCCESS.getMessage());
        apiResponse.setResult(paymentGatewayService.zalopayQueryV2(request));

        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("/zalopay/v2/refund")
    public ResponseEntity<ApiResponse> refundZaloPayV2(@RequestBody @Valid ZaloPayRefundRequest request) {
        ApiResponse apiResponse = new ApiResponse();

        apiResponse.setCode(StatusCode.SUCCESS.getCode());
        apiResponse.setMessage(StatusCode.SUCCESS.getMessage());
        apiResponse.setResult(paymentGatewayService.zalopayRefundV2(request));

        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("/zalopay/v2/query-refund")
    public ResponseEntity<ApiResponse> queryRefundZaloPayV2(@RequestBody @Valid ZaloPayQueryRefundRequest request) {
        ApiResponse apiResponse = new ApiResponse();

        apiResponse.setCode(StatusCode.SUCCESS.getCode());
        apiResponse.setMessage(StatusCode.SUCCESS.getMessage());
        apiResponse.setResult(paymentGatewayService.zalopayQueryRefundV2(request));

        return ResponseEntity.ok(apiResponse);
    }
}
