package com.hpbt.transactionservice.controllers;

import com.hpbt.transactionservice.dto.requests.FindTransactionByOrderIdRequest;
import com.hpbt.transactionservice.dto.requests.TransactionRequest;
import com.hpbt.transactionservice.dto.requests.UpdateTransactionStatusRequest;
import com.hpbt.transactionservice.dto.responses.ApiResponse;
import com.hpbt.transactionservice.dto.responses.TransactionResponse;
import com.hpbt.transactionservice.services.TransactionService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/update-transaction")
    public ResponseEntity<ApiResponse<TransactionResponse>> updateTransaction(@RequestBody @Valid UpdateTransactionStatusRequest request) {
        return ResponseEntity.ok(ApiResponse.success(transactionService.updateTransaction(request)));
    }

    @PostMapping("/get-transaction-by-orderId")
    public ResponseEntity<ApiResponse<TransactionResponse>> findTransactionByOrderId(@RequestBody FindTransactionByOrderIdRequest request) {
        return ResponseEntity.ok(ApiResponse.success(transactionService.getTransactionByOrderId(request.orderId())));
    }
}
