package com.hpbt.event;

import java.time.LocalDateTime;

public record MoneyRefund(
        Long id,
        String username,
        String email,
        Long amount,
        String orderId,
        LocalDateTime refundDate,
        String method
) {
}
