package com.hpbt.transactionservice.controllers;

import com.hpbt.transactionservice.dto.requests.PaymentTypesRequest;
import com.hpbt.transactionservice.dto.responses.ApiResponse;
import com.hpbt.transactionservice.dto.responses.PaymentTypesResponse;
import com.hpbt.transactionservice.services.PaymentTypesService;
import com.hpbt.transactionservice.services.TransactionService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/transaction")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PublicTransactionController {
    TransactionService transactionService;
    PaymentTypesService paymentTypesService;

    @PostMapping("/create-payment-types")
    public ResponseEntity<ApiResponse<PaymentTypesResponse>> createPaymentTypes(@RequestBody @Valid PaymentTypesRequest request){
        return ResponseEntity.ok(ApiResponse.success(paymentTypesService.createPaymentTypes(request)));
    }
}
