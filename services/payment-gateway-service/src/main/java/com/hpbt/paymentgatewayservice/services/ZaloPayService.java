package com.hpbt.paymentgatewayservice.services;

import com.hpbt.paymentgatewayservice.dto.requests.PaymentGatewayRequest;
import com.hpbt.paymentgatewayservice.dto.responses.ZalopayResponse;

public interface ZaloPayService {
    ZalopayResponse zalopayCreateV2(PaymentGatewayRequest request);
}
