package com.hpbt.event;

import lombok.Builder;

import java.time.Instant;
import java.time.LocalDateTime;

@Builder
public record MoneyRefund(
        Long id,
        String username,
        String email,
        Long amount,
        String orderId,
        Instant refundDate,
        String method
) {
}
