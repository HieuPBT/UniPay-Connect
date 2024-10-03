package com.hpbt.paymentgatewayservice.services;

import com.hpbt.paymentgatewayservice.dto.requests.PaymentGatewayRequest;
import com.hpbt.paymentgatewayservice.dto.requests.momo.MoMoConfirmRequest;
import com.hpbt.paymentgatewayservice.dto.requests.momo.MoMoCreateRequest;
import com.hpbt.paymentgatewayservice.dto.requests.momo.MoMoQueryRequest;
import com.hpbt.paymentgatewayservice.dto.requests.momo.MoMoRefundRequest;
import com.hpbt.paymentgatewayservice.dto.requests.zalopay.version2.ZaloPayCreateRequest;
import com.hpbt.paymentgatewayservice.dto.requests.zalopay.version2.ZaloPayQueryRefundRequest;
import com.hpbt.paymentgatewayservice.dto.requests.zalopay.version2.ZaloPayQueryRequest;
import com.hpbt.paymentgatewayservice.dto.requests.zalopay.version2.ZaloPayRefundRequest;
import com.hpbt.paymentgatewayservice.dto.responses.PaymentLogResponse;

import java.util.Map;

public interface PaymentGatewayService {
    Map<String, Object> createMoMo(MoMoCreateRequest request);
    Map<String, Object> queryMoMo(MoMoQueryRequest request);
    Map<String, Object> confirmMoMo(MoMoConfirmRequest request);
    Map<String, Object> refundMoMo(MoMoRefundRequest request);

//    ZalopayResponse zalopayCreateV2(PaymentGatewayRequest request);
    Map<String, Object> zalopayCreateV2(ZaloPayCreateRequest request);
    Map<String, Object> zalopayQueryV2(ZaloPayQueryRequest request);
    Map<String, Object> zalopayRefundV2(ZaloPayRefundRequest request);
    Map<String, Object> zalopayQueryRefundV2(ZaloPayQueryRefundRequest request);

    PaymentLogResponse createPaymentLog(PaymentGatewayRequest request);
}
