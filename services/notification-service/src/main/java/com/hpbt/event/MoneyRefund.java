package com.hpbt.event;

import java.time.Instant;
import java.time.LocalDateTime;

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
