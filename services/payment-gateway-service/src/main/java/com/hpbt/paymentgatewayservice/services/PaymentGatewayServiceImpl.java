package com.hpbt.paymentgatewayservice.services;

import com.hpbt.paymentgatewayservice.dto.requests.PaymentGatewayRequest;
import com.hpbt.paymentgatewayservice.dto.responses.MoMoResponse;
import com.hpbt.paymentgatewayservice.dto.responses.ZalopayResponse;
import com.hpbt.paymentgatewayservice.repositories.PaymentGatewayRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentGatewayServiceImpl implements PaymentGatewayService {
    private final PaymentGatewayRepository paymentGatewayRepository;


    @Override
    public MoMoResponse createMoMo(PaymentGatewayRequest request) {
        return null;
    }

    @Override
    public ZalopayResponse createZalopay(PaymentGatewayRequest request) {
        return null;
    }
}
