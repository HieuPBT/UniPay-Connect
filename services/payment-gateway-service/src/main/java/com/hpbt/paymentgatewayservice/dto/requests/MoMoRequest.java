package com.hpbt.paymentgatewayservice.dto.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

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
        String signature,

        @NotNull(message = "ApiKey cannot be null")
        @NotBlank(message = "ApiKey cannot be blank")
        @Size(min = 10, max = 100, message = "ApiKey must be between 10 and 100 characters")
        String apiKey
) {
}
