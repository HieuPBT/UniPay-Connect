package com.hpbt.billingservice.dto.responses;

import com.hpbt.billingservice.entities.UserSubscription;
import lombok.Builder;

@Builder
public record BillingResponse(
        Long id,
        UserSubscription userSubscription,
        Integer requestCount,
        Double amount,
        String billingPeriod
) {
}
