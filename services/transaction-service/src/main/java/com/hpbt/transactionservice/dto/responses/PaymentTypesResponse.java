package com.hpbt.transactionservice.dto.responses;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record PaymentTypesResponse(
        Integer id,
        String name,
        String description,
        boolean isActive,
        LocalDateTime createdAt
) {
}
