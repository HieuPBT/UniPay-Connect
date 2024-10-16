package com.hpbt.notificationservice.entities;

import java.time.LocalDateTime;

public record RefundInfo(
        Long amount,
        String currency,
        String orderId
) {
}
