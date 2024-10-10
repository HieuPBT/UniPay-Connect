package com.hpbt.billingservice.dto.requests;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record BillingCreateRequest(
        @NotNull(message = "userSubscriptionId cannot be null")
        Long userSubscriptionId,

        @NotNull(message = "requestCount cannot be null")
        Integer requestCount,

        @NotNull(message = "amount cannot be null")
        Double amount,

        @NotNull(message = "billingPeriod cannot be null")
        String billingPeriod
) {
}
