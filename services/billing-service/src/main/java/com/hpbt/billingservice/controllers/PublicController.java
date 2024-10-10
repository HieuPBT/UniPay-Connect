package com.hpbt.billingservice.controllers;

import com.hpbt.billingservice.commons.ApiResponse;
import com.hpbt.billingservice.dto.requests.SubscriptionTierCreateRequest;
import com.hpbt.billingservice.dto.requests.SubscriptionTierUpdateRequest;
import com.hpbt.billingservice.dto.responses.SubscriptionTierResponse;
import com.hpbt.billingservice.services.SubscriptionTierService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/billing")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PublicController {
    SubscriptionTierService subscriptionTierService;

    @PostMapping("/create-subscription-tier")
    public ResponseEntity<ApiResponse<SubscriptionTierResponse>> createSubscriptionTier(@RequestBody @Valid SubscriptionTierCreateRequest request) {
        return ResponseEntity.ok(ApiResponse.success(subscriptionTierService.createSubscriptionTier(request)));
    }

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
}
