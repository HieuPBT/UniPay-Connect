package com.hpbt.paymentgatewayservice.dto.requests.momo;

import jakarta.validation.constraints.*;

public record MoMoConfirmRequest(
        @NotNull(message = "partnerCode cannot be null")
        @NotBlank(message = "partnerCode cannot be blank")
        String partnerCode,

        @NotNull(message = "orderId cannot be null")
        @NotBlank(message = "orderId cannot be blank")
        String orderId,

        @NotNull(message = "requestType cannot be null")
        @NotBlank(message = "requestType cannot be blank")
        String requestType,

        @NotNull(message = "amount cannot be null")
        @Positive(message = "amount must be a positive  number")
        Long amount,

        @NotNull(message = "lang cannot be null")
        @NotBlank(message = "lang cannot be blank")
        String lang,

        String description,

//        @NotNull(message = "accessKey cannot be null")
//        @NotBlank(message = "accessKey cannot be blank")
//        String accessKey,
//
//        @NotNull(message = "secretKey cannot be null")
//        @NotBlank(message = "secretKey cannot be blank")
//        String secretKey,

        @NotNull(message = "ApiKey cannot be null")
        @NotBlank(message = "ApiKey cannot be blank")
        @Size(min = 10, max = 100, message = "ApiKey must be between 10 and 100 characters")
        String apiKey
) {
}
