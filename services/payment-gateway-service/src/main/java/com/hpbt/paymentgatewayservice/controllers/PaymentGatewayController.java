package com.hpbt.paymentgatewayservice.controllers;

import com.hpbt.paymentgatewayservice.clients.MoMoClient;
import com.hpbt.paymentgatewayservice.clients.ZaloPayClientV1;
import com.hpbt.paymentgatewayservice.clients.ZaloPayClientV2;
import com.hpbt.paymentgatewayservice.dto.requests.MoMoRequest;
import com.hpbt.paymentgatewayservice.dto.requests.PaymentGatewayRequest;
import com.hpbt.paymentgatewayservice.dto.requests.momo.MoMoConfirmRequest;
import com.hpbt.paymentgatewayservice.dto.requests.momo.MoMoCreateRequest;
import com.hpbt.paymentgatewayservice.dto.requests.momo.MoMoQueryRequest;
import com.hpbt.paymentgatewayservice.dto.requests.momo.MoMoRefundRequest;
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

    @PostMapping("/momo/create")
    public ResponseEntity<ApiResponse> createMoMo(@RequestBody @Valid MoMoCreateRequest request) {
        ApiResponse apiResponse = new ApiResponse();

        apiResponse.setCode(StatusCode.SUCCESS.getCode());
        apiResponse.setMessage(StatusCode.SUCCESS.getMessage());

        apiResponse.setResult(paymentGatewayService.createMoMo(request));

        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("/momo/query")
    public ResponseEntity<ApiResponse> queryMoMo(@RequestBody @Valid MoMoQueryRequest request){
        ApiResponse apiResponse = new ApiResponse();

        apiResponse.setCode(StatusCode.SUCCESS.getCode());
        apiResponse.setMessage(StatusCode.SUCCESS.getMessage());

        apiResponse.setResult(paymentGatewayService.queryMoMo(request));

        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("/momo/confirm")
    public ResponseEntity<ApiResponse> confirmMoMo(@RequestBody @Valid MoMoConfirmRequest request){
        ApiResponse apiResponse = new ApiResponse();

        apiResponse.setCode(StatusCode.SUCCESS.getCode());
        apiResponse.setMessage(StatusCode.SUCCESS.getMessage());

        apiResponse.setResult(paymentGatewayService.confirmMoMo(request));

        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("/momo/refund")
    public ResponseEntity<ApiResponse> refundMoMo(@RequestBody @Valid MoMoRefundRequest request){
        ApiResponse apiResponse = new ApiResponse();

        apiResponse.setCode(StatusCode.SUCCESS.getCode());
        apiResponse.setMessage(StatusCode.SUCCESS.getMessage());

        apiResponse.setResult(paymentGatewayService.refundMoMo(request));

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
