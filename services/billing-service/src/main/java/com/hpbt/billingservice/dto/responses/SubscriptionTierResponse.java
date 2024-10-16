package com.hpbt.billingservice.dto.responses;

import lombok.Builder;

@Builder
public record SubscriptionTierResponse(
        Long id,
        String name,
        Integer maxRequests,
        Double price
) {
}
