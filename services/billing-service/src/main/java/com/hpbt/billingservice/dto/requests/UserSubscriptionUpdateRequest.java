package com.hpbt.billingservice.dto.requests;

import com.hpbt.billingservice.entities.SubscriptionTier;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record UserSubscriptionUpdateRequest(
        @NotNull(message = "id cannot be null")
        Long id,

        @NotNull(message = "subscriptionTierId cannot be null")
        long subscriptionTierId
) {
}
