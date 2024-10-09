package com.hpbt.paymentgatewayservice.dto.requests;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record UpdateTransactionStatusRequest(
        @NotNull(message = "transactionId cannot be null")
        Integer transactionId,

        @NotNull(message = "status cannot be null")
        Status status
) {
}
