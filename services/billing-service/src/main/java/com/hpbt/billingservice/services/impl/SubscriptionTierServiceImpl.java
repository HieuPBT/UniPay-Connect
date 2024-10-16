package com.hpbt.billingservice.services.impl;

import com.hpbt.billingservice.commons.StatusCode;
import com.hpbt.billingservice.dto.requests.SubscriptionTierCreateRequest;
import com.hpbt.billingservice.dto.requests.SubscriptionTierUpdateRequest;
import com.hpbt.billingservice.dto.responses.SubscriptionTierResponse;
import com.hpbt.billingservice.entities.SubscriptionTier;
import com.hpbt.billingservice.exceptions.CustomException;
import com.hpbt.billingservice.mappers.BillingMapper;
import com.hpbt.billingservice.repositories.SubscriptionTierRepository;
import com.hpbt.billingservice.services.SubscriptionTierService;
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
public class SubscriptionTierServiceImpl implements SubscriptionTierService {
    SubscriptionTierRepository subscriptionTierRepository;
    BillingMapper billingMapper;

    @Override
    public SubscriptionTierResponse createSubscriptionTier(SubscriptionTierCreateRequest request) {
        SubscriptionTier subscriptionTier = subscriptionTierRepository.findByName(request.name());
        if(subscriptionTier == null){
            return billingMapper.toSubscriptionTierResponse(subscriptionTierRepository.save(billingMapper.toSubscriptionTier(request)));
        }
        throw new CustomException(StatusCode.BAD_REQUEST, "duplicate subscription tier name");
    }

    @Override
    public SubscriptionTierResponse updateSubscriptionTier(SubscriptionTierUpdateRequest request) {
        SubscriptionTier subscriptionTier = subscriptionTierRepository.findById(request.id())
                .orElseThrow(() -> new CustomException(StatusCode.BAD_REQUEST, "subscription tier not found"));
        if (request.name() != null) {
            subscriptionTier.setName(request.name());
        }
        if (request.maxRequests() != null) {
            subscriptionTier.setMaxRequests(request.maxRequests());
        }
        if (request.price() != null) {
            subscriptionTier.setPrice(request.price());
        }
        subscriptionTierRepository.save(subscriptionTier);
        return billingMapper.toSubscriptionTierResponse(subscriptionTier);
    }

    @Override
    public SubscriptionTierResponse getSubscriptionTier(Long tierId) {
        SubscriptionTier subscriptionTier = subscriptionTierRepository.findById(tierId)
                .orElseThrow(() -> new CustomException(StatusCode.BAD_REQUEST, "subscription tier not found"));
        return billingMapper.toSubscriptionTierResponse(subscriptionTier);
    }

    @Override
    public List<SubscriptionTierResponse> getAllSubscriptionTiers() {
        List<SubscriptionTier> subscriptionTiers = subscriptionTierRepository.findAll();
        List<SubscriptionTierResponse> subscriptionTierResponses = subscriptionTiers
                .stream().map(billingMapper::toSubscriptionTierResponse)
                .collect(Collectors.toList());
        return subscriptionTierResponses;
    }
}
