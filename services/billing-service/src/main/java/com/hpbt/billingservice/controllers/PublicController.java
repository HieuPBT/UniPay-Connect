package com.hpbt.billingservice.controllers;

import com.hpbt.billingservice.commons.ApiResponse;
import com.hpbt.billingservice.dto.requests.SubscriptionTierCreateRequest;
import com.hpbt.billingservice.dto.requests.SubscriptionTierUpdateRequest;
import com.hpbt.billingservice.dto.responses.BillingResponse;
import com.hpbt.billingservice.dto.responses.SubscriptionTierResponse;
import com.hpbt.billingservice.services.BillingService;
import com.hpbt.billingservice.services.SubscriptionTierService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/billing")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PublicController {
    SubscriptionTierService subscriptionTierService;
    BillingService billingService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create-subscription-tier")
    public ResponseEntity<ApiResponse<SubscriptionTierResponse>> createSubscriptionTier(@RequestBody @Valid SubscriptionTierCreateRequest request) {
        return ResponseEntity.ok(ApiResponse.success(subscriptionTierService.createSubscriptionTier(request)));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/update-subscription-tier")
    public ResponseEntity<ApiResponse<SubscriptionTierResponse>> updateSubscriptionTier(@RequestBody @Valid SubscriptionTierUpdateRequest request) {
        return ResponseEntity.ok(ApiResponse.success(subscriptionTierService.updateSubscriptionTier(request)));
    }

    @GetMapping("/get-subscription-tier/{id}")
    public ResponseEntity<ApiResponse<SubscriptionTierResponse>> getSubscriptionTier(@PathVariable("id") Long id) {
        return ResponseEntity.ok(ApiResponse.success(subscriptionTierService.getSubscriptionTier(id)));
    }


    @GetMapping("/get-subscription-tier")
    public ResponseEntity<ApiResponse<List<SubscriptionTierResponse>>> getSubscriptionTiers() {
        return ResponseEntity.ok(ApiResponse.success(subscriptionTierService.getAllSubscriptionTiers()));
    }

    @GetMapping("/get-billing/current-user")
    public ResponseEntity<ApiResponse<?>> getAllCurrentUserBilling(
            @RequestParam(value = "startDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
            @RequestParam(value = "endDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size){
        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Long userId = (Long) jwt.getClaims().get("userId");
        Integer userIdInteger = userId.intValue();

        Pageable pageable = PageRequest.of(page, size);

//        Page<BillingResponse> billingResponses = billingService.getAllBillingByUserId(userIdInteger, )
        return null;
    }
}
