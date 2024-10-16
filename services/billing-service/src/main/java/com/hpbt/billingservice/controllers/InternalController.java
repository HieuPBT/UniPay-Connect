package com.hpbt.billingservice.controllers;

import com.hpbt.billingservice.commons.ApiResponse;
import com.hpbt.billingservice.dto.requests.BillingCreateRequest;
import com.hpbt.billingservice.dto.requests.BillingUpdateRequest;
import com.hpbt.billingservice.dto.requests.UserSubscriptionCreateRequest;
import com.hpbt.billingservice.dto.requests.UserSubscriptionUpdateRequest;
import com.hpbt.billingservice.dto.responses.BillingResponse;
import com.hpbt.billingservice.dto.responses.UserSubscriptionResponse;
import com.hpbt.billingservice.services.BillingService;
import com.hpbt.billingservice.services.UserSubscriptionService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/internal/v1/billing")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class InternalController {
    UserSubscriptionService userSubscriptionService;
    BillingService billingService;

    @PostMapping("/create-user-subscription")
    public ResponseEntity<ApiResponse<UserSubscriptionResponse>> createUserSubscription(@RequestBody @Valid UserSubscriptionCreateRequest request){
        return ResponseEntity.ok(ApiResponse.success(userSubscriptionService.createUserSubscription(request)));
    }

    @PostMapping("/update-user-subscription")
    public ResponseEntity<ApiResponse<UserSubscriptionResponse>> updateUserSubscription(@RequestBody @Valid UserSubscriptionUpdateRequest request){
        return ResponseEntity.ok(ApiResponse.success(userSubscriptionService.updateUserSubscription(request)));
    }

    @GetMapping("/get-user-subscription")
    public ResponseEntity<ApiResponse<List<UserSubscriptionResponse>>> getAllUserSubscription(){
        return ResponseEntity.ok(ApiResponse.success(userSubscriptionService.getAllUserSubscriptions()));
    }

    @GetMapping("/get-user-subscription/{id}")
    public ResponseEntity<ApiResponse<UserSubscriptionResponse>> getUserSubscriptionById(@PathVariable(value = "id") Long id){
        return ResponseEntity.ok(ApiResponse.success(userSubscriptionService.getUserSubscriptionById(id)));
    }

    @GetMapping("/get-user-subscription/user/{id}")
    public ResponseEntity<ApiResponse<UserSubscriptionResponse>> getUserSubscriptionByUserId(@PathVariable(value = "id") Long id){
        return ResponseEntity.ok(ApiResponse.success(userSubscriptionService.getUserSubscriptionById(id)));
    }

    @PostMapping("/create-billing")
    public ResponseEntity<ApiResponse<BillingResponse>> createBilling(@RequestBody @Valid BillingCreateRequest request){
        return ResponseEntity.ok(ApiResponse.success(billingService.createBilling(request)));
    }

    @PostMapping("/update-billing")
    public ResponseEntity<ApiResponse<BillingResponse>> updateBilling(@RequestBody @Valid BillingUpdateRequest request){
        return ResponseEntity.ok(ApiResponse.success(billingService.updateBilling(request)));
    }

    @GetMapping("/get-billing/{id}")
    public ResponseEntity<ApiResponse<BillingResponse>> getBillingById(@PathVariable(value = "id") Long id){
        return ResponseEntity.ok(ApiResponse.success(billingService.getBillingById(id)));
    }

    @GetMapping("/get-billing")
    public ResponseEntity<ApiResponse<List<BillingResponse>>> getAllBilling(){
        return ResponseEntity.ok(ApiResponse.success(billingService.getAllBillings()));
    }

    @GetMapping("/get-billing/user-subscription/{id}")
    public ResponseEntity<ApiResponse<List<BillingResponse>>> getAllBillingByUserSubscriptionId(@PathVariable(value = "id") Long id){
        return ResponseEntity.ok(ApiResponse.success(billingService.getAllBillingsByUserSubscriptionId(id)));
    }
}
