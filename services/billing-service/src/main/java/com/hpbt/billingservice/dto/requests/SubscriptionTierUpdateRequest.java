package com.hpbt.billingservice.dto.requests;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record SubscriptionTierUpdateRequest(
        Long id,
        String name,
        Integer maxRequests,
        Double price
) {
}
