package com.hpbt.paymentgatewayservice.dto.requests;

import com.hpbt.paymentgatewayservice.entities.Status;
import jakarta.validation.constraints.*;
import lombok.Builder;

@Builder
public record PaymentGatewayRequest(
        @NotNull(message = "requestUrl cannot be null")
        @NotBlank(message = "requestUrl cannot be blank")
        String requestUrl,

        @NotNull(message = "status cannot be null")
        Status status,

        String context,

        @NotNull(message = "transactionId cannot be null")
        @NotBlank(message = "transactionId cannot be blank")
        String transactionId
) {
}

