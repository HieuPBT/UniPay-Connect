package com.hpbt.paymentgatewayservice.dto.requests;

public record MoMoRequest(
        String partnerCode,
        String requestId,
        long amount,
        String orderId,
        String ipnUrl,
        String redirectUrl,
        String orderInfo,
        String requestType,
        String extraData,
        String lang,
        String signature
) {
}
