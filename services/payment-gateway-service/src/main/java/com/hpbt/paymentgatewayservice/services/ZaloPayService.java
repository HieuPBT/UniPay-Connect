package com.hpbt.paymentgatewayservice.services;

import com.hpbt.paymentgatewayservice.dto.requests.PaymentGatewayRequest;
import com.hpbt.paymentgatewayservice.dto.requests.zalopay.version2.ZaloPayCreateRequest;

import java.util.Map;

public interface ZaloPayService {
    Map<String, Object> zalopayCreateV2(ZaloPayCreateRequest request);
    Map<String, Object> zalopayQueryV2(ZaloPayCreateRequest request);
}
