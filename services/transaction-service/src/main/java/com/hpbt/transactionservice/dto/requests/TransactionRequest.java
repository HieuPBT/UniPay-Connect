package com.hpbt.transactionservice.dto.requests;

import com.hpbt.transactionservice.entities.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record TransactionRequest(
        @NotNull(message = "userId cannot be null")
        Integer userId,

        @NotNull(message = "amount cannot be null")
        Long amount,

        @NotBlank(message = "currency cannot be blank")
        @NotNull(message = "currency cannot be null")
        String currency,

        @NotNull(message = "status cannot be null")
        Status status,

        @NotBlank(message = "orderId cannot be blank")
        @NotNull(message = "orderId cannot be null")
        String orderId,

        @NotNull(message = "paymentTypeId cannot be null")
        Integer paymentTypeId
) {
}
