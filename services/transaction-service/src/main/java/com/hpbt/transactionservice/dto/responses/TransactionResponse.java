package com.hpbt.transactionservice.dto.responses;

import com.hpbt.transactionservice.entities.PaymentTypes;
import com.hpbt.transactionservice.entities.Status;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record TransactionResponse(
        Integer id,
        Integer userId,
        Long amount,
        String currency,
        Status status,
        String orderId,
        LocalDateTime createdAt,
        PaymentTypes paymentType
) {
}
