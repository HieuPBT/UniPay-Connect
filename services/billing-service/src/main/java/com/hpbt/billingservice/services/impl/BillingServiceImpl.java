package com.hpbt.billingservice.services.impl;

import com.hpbt.billingservice.commons.StatusCode;
import com.hpbt.billingservice.dto.requests.BillingCreateRequest;
import com.hpbt.billingservice.dto.requests.BillingUpdateRequest;
import com.hpbt.billingservice.dto.responses.BillingResponse;
import com.hpbt.billingservice.entities.Billing;
import com.hpbt.billingservice.entities.UserSubscription;
import com.hpbt.billingservice.exceptions.CustomException;
import com.hpbt.billingservice.mappers.BillingMapper;
import com.hpbt.billingservice.repositories.BillingRepository;
import com.hpbt.billingservice.repositories.SubscriptionTierRepository;
import com.hpbt.billingservice.repositories.UserSubscriptionRepository;
import com.hpbt.billingservice.services.BillingService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BillingServiceImpl implements BillingService {
    SubscriptionTierRepository subscriptionTierRepository;
    BillingRepository billingRepository;
    UserSubscriptionRepository userSubscriptionRepository;
    BillingMapper billingMapper;


    @Override
    public BillingResponse createBilling(BillingCreateRequest request) {
        Billing billing = billingRepository.save(billingMapper.toBilling(request));
        return billingMapper.toBillingResponse(billing);
    }

    @Override
    public BillingResponse updateBilling(BillingUpdateRequest request) {
        Billing billing = billingRepository.findById(request.id())
                .orElseThrow(() -> new CustomException(StatusCode.BAD_REQUEST, "Billing not found"));
        if(request.userSubscriptionId() != null){
            UserSubscription userSubscription = userSubscriptionRepository.findById(request.userSubscriptionId())
                    .orElseThrow(() -> new CustomException(StatusCode.BAD_REQUEST, "UserSubscription not found"));
            billing.setUserSubscription(userSubscription);
        }
        if (request.requestCount() != null) {
            billing.setRequestCount(request.requestCount());
        }
        if (request.billingPeriod() != null) {
            billing.setBillingPeriod(request.billingPeriod());
        }
        if (request.amount() != null) {
            billing.setAmount(request.amount());
        }
        if(request.status() != null){
            billing.setStatus(request.status());
        }
        return billingMapper.toBillingResponse(billingRepository.save(billing));
    }

    @Override
    public BillingResponse getBillingById(Long billingId) {
        Billing billing = billingRepository.findById(billingId)
                .orElseThrow(() -> new CustomException(StatusCode.BAD_REQUEST, "Billing not found"));
        return billingMapper.toBillingResponse(billing);
    }

    @Override
    public List<BillingResponse> getAllBillings() {
        List<Billing> billings = billingRepository.findAll();
        List<BillingResponse> billingResponses = billings
                .stream().map(billingMapper::toBillingResponse)
                .collect(Collectors.toList());
        return billingResponses;
    }

    @Override
    public List<BillingResponse> getAllBillingsByUserSubscriptionId(Long userSubscriptionId) {
        List<Billing> billings = billingRepository.findByUserSubscriptionId(userSubscriptionId);
        List<BillingResponse> billingResponses = billings
                .stream().map(billingMapper::toBillingResponse)
                .collect(Collectors.toList());
        return billingResponses;
    }

    @Override
    public Page<BillingResponse> getAllBillingByUserId(Long userId, Instant startDate, Instant endDate, Pageable pageable) {
        Page<Billing> billings = billingRepository.findAllByUserId(userId, startDate, endDate, pageable);

        List<BillingResponse> billingResponses = billings.getContent()
                .stream()
                .map(billingMapper::toBillingResponse)
                .collect(Collectors.toList());

        return new PageImpl<>(billingResponses, pageable, billings.getTotalElements());
    }
}
