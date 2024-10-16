package com.hpbt.billingservice.services;

import com.hpbt.billingservice.dto.requests.BillingCreateRequest;
import com.hpbt.billingservice.dto.requests.BillingUpdateRequest;
import com.hpbt.billingservice.dto.responses.BillingResponse;
import com.hpbt.billingservice.entities.Billing;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.Instant;
import java.util.List;

public interface BillingService {
    BillingResponse createBilling(BillingCreateRequest request);
    BillingResponse updateBilling(BillingUpdateRequest request);
    BillingResponse getBillingById(Long billingId);
//    BillingResponse getBillingByUserSubscriptionId(Long userSubscriptionId);
    List<BillingResponse> getAllBillings();
    List<BillingResponse> getAllBillingsByUserSubscriptionId(Long userSubscriptionId);
    Page<BillingResponse> getAllBillingByUserId(Long userId, Instant startDate, Instant endDate, Pageable pageable);
}
