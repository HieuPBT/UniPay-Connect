package com.hpbt.billingservice.services.impl;

import com.hpbt.billingservice.commons.StatusCode;
import com.hpbt.billingservice.dto.requests.UserSubscriptionCreateRequest;
import com.hpbt.billingservice.dto.requests.UserSubscriptionUpdateRequest;
import com.hpbt.billingservice.dto.responses.UserSubscriptionResponse;
import com.hpbt.billingservice.entities.SubscriptionTier;
import com.hpbt.billingservice.entities.UserSubscription;
import com.hpbt.billingservice.exceptions.CustomException;
import com.hpbt.billingservice.mappers.BillingMapper;
import com.hpbt.billingservice.repositories.SubscriptionTierRepository;
import com.hpbt.billingservice.repositories.UserSubscriptionRepository;
import com.hpbt.billingservice.services.UserSubscriptionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserSubscriptionServiceImpl implements UserSubscriptionService {
    UserSubscriptionRepository userSubscriptionRepository;
    SubscriptionTierRepository subscriptionTierRepository;
    BillingMapper billingMapper;

    @Override
    public UserSubscriptionResponse createUserSubscription(UserSubscriptionCreateRequest request) {
        UserSubscription userSubscription = userSubscriptionRepository.save(billingMapper.toUserSubscription(request));
        return billingMapper.toUserSubscriptionResponse(userSubscription);
    }

    @Override
    public UserSubscriptionResponse updateUserSubscription(UserSubscriptionUpdateRequest request) {
        UserSubscription userSubscription = userSubscriptionRepository.findById(request.id())
                .orElseThrow(() -> new CustomException(StatusCode.BAD_REQUEST, "User Subscription Not Found"));

        SubscriptionTier subscriptionTier = subscriptionTierRepository.findById(request.subscriptionTierId())
                .orElseThrow(() -> new CustomException(StatusCode.BAD_REQUEST, "Subscription Tier Not Found"));

        userSubscription.setSubscriptionTier(subscriptionTier);
        return billingMapper.toUserSubscriptionResponse(userSubscriptionRepository.save(userSubscription));
    }

    @Override
    public List<UserSubscriptionResponse> getAllUserSubscriptions() {
        List<UserSubscription> userSubscriptions = userSubscriptionRepository.findAll();
        List<UserSubscriptionResponse> userSubscriptionResponses = userSubscriptions
                .stream().map(billingMapper::toUserSubscriptionResponse)
                .collect(Collectors.toList());
        return userSubscriptionResponses;
    }

    @Override
    public List<UserSubscriptionResponse> getAllUserSubscriptionsByUserId(Long userId) {
        List<UserSubscription> userSubscriptions = userSubscriptionRepository.findByUserId(userId);
        List<UserSubscriptionResponse> userSubscriptionResponses = userSubscriptions
                .stream().map(billingMapper::toUserSubscriptionResponse)
                .collect(Collectors.toList());
        return userSubscriptionResponses;
    }

    @Override
    public UserSubscriptionResponse getUserSubscriptionById(Long id) {
        UserSubscription userSubscription = userSubscriptionRepository.findById(id)
                .orElseThrow(() -> new CustomException(StatusCode.BAD_REQUEST, "User Subscription Not Found"));
        return billingMapper.toUserSubscriptionResponse(userSubscription);
    }
}
