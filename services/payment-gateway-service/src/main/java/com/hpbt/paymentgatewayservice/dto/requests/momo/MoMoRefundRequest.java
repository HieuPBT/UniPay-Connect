package com.hpbt.paymentgatewayservice.dto.requests.momo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record MoMoRefundRequest(
        @NotNull(message = "partnerCode cannot be null")
        @NotBlank(message = "partnerCode cannot be blank")
        String partnerCode,

        @NotNull(message = "amount cannot be null")
        @Positive(message = "amount must be a positive  number")
        Long amount,

        @NotNull(message = "transId cannot be null")
        @Positive(message = "transId must be a positive  number")
        Long transId,

        @NotNull(message = "lang cannot be null")
        @NotBlank(message = "lang cannot be blank")
        String lang,

        String description,

        @NotNull(message = "accessKey cannot be null")
        @NotBlank(message = "accessKey cannot be blank")
        String accessKey,

        @NotNull(message = "secretKey cannot be null")
        @NotBlank(message = "secretKey cannot be blank")
        String secretKey,

        @NotNull(message = "ApiKey cannot be null")
        @NotBlank(message = "ApiKey cannot be blank")
        @Size(min = 10, max = 100, message = "ApiKey must be between 10 and 100 characters")
        String apiKey
) {
}
