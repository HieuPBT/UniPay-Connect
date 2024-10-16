package com.hpbt.paymentgatewayservice.dto.responses;

public record PaymentGatewayResponse(
        String orderId,
        String requestId

) {
}
