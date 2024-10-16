package com.hpbt.billingservice.dto.responses;

import com.hpbt.billingservice.entities.SubscriptionTier;
import lombok.Builder;

import java.time.Instant;

@Builder
public record UserSubscriptionResponse(
        Long id,
        Long userId,
        SubscriptionTier subscriptionTier,
        Instant startDate,
        Instant endDate
) {
}
