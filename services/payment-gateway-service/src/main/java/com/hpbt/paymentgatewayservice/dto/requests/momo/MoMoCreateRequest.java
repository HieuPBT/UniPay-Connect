package com.hpbt.paymentgatewayservice.dto.requests.momo;

import jakarta.validation.constraints.*;

public record MoMoCreateRequest(
        @NotNull(message = "partnerCode cannot be null")
        @NotBlank(message = "partnerCode cannot be blank")
        String partnerCode,

        @NotNull(message = "orderInfo cannot be null")
        @NotBlank(message = "orderInfo cannot be blank")
        String orderInfo,

        @NotNull(message = "amount cannot be null")
        @Positive(message = "amount must be a positive  number")
        @Min(value = 10000, message = "amount must be at least 10,000 VND")
        @Max(value = 50000000, message = "amount must not exceed 50,000,000 VND")
        Long amount,

        @NotNull(message = "redirectUrl cannot be null")
        @NotBlank(message = "redirectUrl cannot be blank")
        @Pattern(regexp = "^(https?|ftp)://[^\\s/$.?#].[^\\s]*$", message = "redirectUrl must be a valid URL")
        String redirectUrl,

        @NotNull(message = "ipnUrl cannot be null")
        @NotBlank(message = "ipnUrl cannot be blank")
        @Pattern(regexp = "^(https?|ftp)://[^\\s/$.?#].[^\\s]*$", message = "ipnUrl must be a valid URL")
        String ipnUrl,

        @NotNull(message = "requestType cannot be null")
        @NotBlank(message = "requestType cannot be blank")
        String requestType,

        String extraData,

        String items,

        Boolean autoCapture,

        @NotNull(message = "lang cannot be null")
        @NotBlank(message = "lang cannot be blank")
        String lang,

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
