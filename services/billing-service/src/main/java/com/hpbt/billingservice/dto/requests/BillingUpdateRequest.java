package com.hpbt.billingservice.dto.requests;

import com.hpbt.billingservice.entities.BillingStatus;
import jakarta.validation.constraints.NotNull;

public record BillingUpdateRequest(
        @NotNull(message = "id cannot be null")
        Long id,

        Long userSubscriptionId,

        Integer requestCount,

        Double amount,

        String billingPeriod,

        BillingStatus status
) {
}
