package com.hpbt.paymentgatewayservice.dto.requests;

import jakarta.validation.constraints.*;


public record PaymentGatewayRequest(

        @NotNull(message = "Amount cannot be null")
        @Min(value = 1, message = "Amount must be greater than 0")
        long amount,

        @NotNull(message = "ApiKey cannot be null")
        @NotBlank(message = "ApiKey cannot be blank")
        @Size(min = 10, max = 100, message = "ApiKey must be between 10 and 100 characters")
        String apiKey,

        @NotNull(message = "RedirectUrl cannot be null")
        @NotBlank(message = "RedirectUrl cannot be blank")
        @Size(max = 200, message = "RedirectUrl cannot exceed 200 characters")
        @Pattern(regexp = "^(https?|ftp)://[^\\s/$.?#].[^\\s]*$", message = "RedirectUrl must be a valid URL")
        String redirectUrl,

        @NotNull(message = "CallbackUrl cannot be null")
        @NotBlank(message = "CallbackUrl cannot be blank")
        @Size(max = 200, message = "CallbackUrl cannot exceed 200 characters")
        @Pattern(regexp = "^(https?|ftp)://[^\\s/$.?#].[^\\s]*$", message = "CallbackUrl must be a valid URL")
        String callbackUrl
) {
}

