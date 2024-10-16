package com.hpbt.billingservice.mappers;

import com.hpbt.billingservice.commons.StatusCode;
import com.hpbt.billingservice.dto.requests.BillingCreateRequest;
import com.hpbt.billingservice.dto.requests.SubscriptionTierCreateRequest;
import com.hpbt.billingservice.dto.requests.UserSubscriptionCreateRequest;
import com.hpbt.billingservice.dto.responses.BillingResponse;
import com.hpbt.billingservice.dto.responses.SubscriptionTierResponse;
import com.hpbt.billingservice.dto.responses.UserSubscriptionResponse;
import com.hpbt.billingservice.entities.Billing;
import com.hpbt.billingservice.entities.BillingStatus;
import com.hpbt.billingservice.entities.SubscriptionTier;
import com.hpbt.billingservice.entities.UserSubscription;
import com.hpbt.billingservice.exceptions.CustomException;
import com.hpbt.billingservice.repositories.BillingRepository;
import com.hpbt.billingservice.repositories.SubscriptionTierRepository;
import com.hpbt.billingservice.repositories.UserSubscriptionRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class BillingMapper {
    SubscriptionTierRepository subscriptionTierRepository;
    UserSubscriptionRepository userSubscriptionRepository;
    BillingRepository BillingRepository;

    public SubscriptionTierResponse toSubscriptionTierResponse(SubscriptionTier subscriptionTier) {
        return SubscriptionTierResponse.builder()
                .id(subscriptionTier.getId())
                .name(subscriptionTier.getName())
                .maxRequests(subscriptionTier.getMaxRequests())
                .price(subscriptionTier.getPrice())
                .build();
    }

    public SubscriptionTier toSubscriptionTier(SubscriptionTierCreateRequest request) {
        return SubscriptionTier.builder()
                .name(request.name())
                .maxRequests(request.maxRequests())
                .price(request.price())
                .build();
    }

    public UserSubscriptionResponse toUserSubscriptionResponse(UserSubscription userSubscription) {
        return UserSubscriptionResponse.builder()
                .id(userSubscription.getId())
                .userId(userSubscription.getUserId())
                .subscriptionTier(userSubscription.getSubscriptionTier())
                .startDate(userSubscription.getStartDate())
                .endDate(userSubscription.getEndDate())
                .build();
    }

    public UserSubscription toUserSubscription(UserSubscriptionCreateRequest request) {
        SubscriptionTier subscriptionTier = subscriptionTierRepository.findById(request.subscriptionTierId())
                .orElseThrow(() -> new CustomException(StatusCode.BAD_REQUEST, "Subscription Tier Not Found"));
        return UserSubscription.builder()
                .userId(request.userId())
                .subscriptionTier(subscriptionTier)
                .startDate(Instant.now())
                .endDate(Instant.now().plus(30, ChronoUnit.DAYS))
                .build();
    }

    public BillingResponse toBillingResponse(Billing billing) {
        return BillingResponse.builder()
                .id(billing.getId())
                .userSubscription(billing.getUserSubscription())
                .requestCount(billing.getRequestCount())
                .amount(billing.getAmount())
                .billingPeriod(billing.getBillingPeriod())
                .build();
    }

    public Billing toBilling(BillingCreateRequest request) {
        UserSubscription userSubscription = userSubscriptionRepository.findById(request.userSubscriptionId())
                .orElseThrow(() -> new CustomException(StatusCode.BAD_REQUEST, "User Subscription Not Found"));
        return Billing.builder()
                .userSubscription(userSubscription)
                .requestCount(request.requestCount())
                .amount(request.amount())
                .billingPeriod(request.billingPeriod())
                .status(BillingStatus.PENDING)
                .build();
    }
}
