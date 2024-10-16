package com.hpbt.billingservice.dto.requests;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.NonNull;

@Builder
public record SubscriptionTierCreateRequest(
        @NotNull(message = "name cannot be null")
        String name,

        @NotNull(message = "maxRequests cannot be null")
        Integer maxRequests,

        @NotNull(message = "price cannot be null")
        Double price
) {
}
