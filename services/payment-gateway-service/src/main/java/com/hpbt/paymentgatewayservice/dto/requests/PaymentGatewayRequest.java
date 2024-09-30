package com.hpbt.paymentgatewayservice.dto.requests;

import jakarta.validation.constraints.*;


public record PaymentGatewayRequest(
        @NotNull(message = "ApiKey cannot be null")
        @NotBlank(message = "ApiKey cannot be blank")
        @Size(min = 10, max = 100, message = "ApiKey must be between 10 and 100 characters")
        String apiKey
) {
}

