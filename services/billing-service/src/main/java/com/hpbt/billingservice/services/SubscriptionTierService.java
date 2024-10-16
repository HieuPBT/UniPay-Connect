package com.hpbt.billingservice.services;

import com.hpbt.billingservice.dto.requests.SubscriptionTierCreateRequest;
import com.hpbt.billingservice.dto.requests.SubscriptionTierUpdateRequest;
import com.hpbt.billingservice.dto.responses.SubscriptionTierResponse;
import com.hpbt.billingservice.entities.SubscriptionTier;

import java.util.List;

public interface SubscriptionTierService {
    SubscriptionTierResponse createSubscriptionTier(SubscriptionTierCreateRequest request);
    SubscriptionTierResponse updateSubscriptionTier(SubscriptionTierUpdateRequest request);
    SubscriptionTierResponse getSubscriptionTier(Long tierId);
    List<SubscriptionTierResponse> getAllSubscriptionTiers();
}
