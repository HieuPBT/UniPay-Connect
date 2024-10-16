package com.hpbt.billingservice.services;

import com.hpbt.billingservice.dto.requests.UserSubscriptionCreateRequest;
import com.hpbt.billingservice.dto.requests.UserSubscriptionUpdateRequest;
import com.hpbt.billingservice.dto.responses.UserSubscriptionResponse;

import java.util.List;

public interface UserSubscriptionService {
    UserSubscriptionResponse createUserSubscription(UserSubscriptionCreateRequest request);
    UserSubscriptionResponse updateUserSubscription(UserSubscriptionUpdateRequest request);
    List<UserSubscriptionResponse> getAllUserSubscriptions();
    List<UserSubscriptionResponse> getAllUserSubscriptionsByUserId(Long userId);
    UserSubscriptionResponse getUserSubscriptionById(Long id);
}
