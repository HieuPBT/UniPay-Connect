package com.hpbt.billingservice.dto.requests;

import com.hpbt.billingservice.entities.SubscriptionTier;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.Instant;

@Builder
public record UserSubscriptionCreateRequest(
        @NotNull(message = "userId cannot be null")
        Long userId,

        @NotNull(message = "subscriptionTierId cannot be null")
        long subscriptionTierId
) {
}
