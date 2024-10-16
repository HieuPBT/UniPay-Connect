package com.hpbt.paymentgatewayservice.dto.responses;

public record MoMoResponse(
        String partnerCode,
        String orderId,
        String requestId,
        long amount,
        Long responseTime,
        String message,
        int resultCode,
        String payUrl,
        String deepLink,
        String qrCoddeUrl
) {
}
