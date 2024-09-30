package com.hpbt.paymentgatewayservice.services;

import com.hpbt.paymentgatewayservice.dto.requests.PaymentGatewayRequest;
import com.hpbt.paymentgatewayservice.dto.responses.MoMoResponse;
import com.hpbt.paymentgatewayservice.dto.responses.ZalopayResponse;

public interface PaymentGatewayService {
    MoMoResponse createMoMo(PaymentGatewayRequest request);
    ZalopayResponse createZalopay(PaymentGatewayRequest request);
}
