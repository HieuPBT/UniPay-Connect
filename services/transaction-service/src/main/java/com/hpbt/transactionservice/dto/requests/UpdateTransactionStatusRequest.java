package com.hpbt.transactionservice.dto.requests;

import com.hpbt.transactionservice.entities.Status;
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
