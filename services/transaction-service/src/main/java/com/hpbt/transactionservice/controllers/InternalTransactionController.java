package com.hpbt.transactionservice.controllers;

import com.hpbt.transactionservice.dto.requests.TransactionRequest;
import com.hpbt.transactionservice.dto.responses.ApiResponse;
import com.hpbt.transactionservice.dto.responses.TransactionResponse;
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
@RequestMapping("/internal/v1/transaction")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class InternalTransactionController {
    TransactionService transactionService;

    @PostMapping("/create-transaction")
    public ResponseEntity<ApiResponse<TransactionResponse>> createTransaction(@RequestBody @Valid TransactionRequest transactionRequest) {
        return ResponseEntity.ok(ApiResponse.success(transactionService.createTransaction(transactionRequest)));
    }
}
