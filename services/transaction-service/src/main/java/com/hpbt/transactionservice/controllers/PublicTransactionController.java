package com.hpbt.transactionservice.controllers;

import com.hpbt.transactionservice.dto.requests.PaymentTypesRequest;
import com.hpbt.transactionservice.dto.responses.ApiResponse;
import com.hpbt.transactionservice.dto.responses.PaymentTypesResponse;
import com.hpbt.transactionservice.dto.responses.TransactionResponse;
import com.hpbt.transactionservice.services.PaymentTypesService;
import com.hpbt.transactionservice.services.TransactionService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/transaction")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class PublicTransactionController {
    TransactionService transactionService;
    PaymentTypesService paymentTypesService;

    @PostMapping("/create-payment-types")
    public ResponseEntity<ApiResponse<PaymentTypesResponse>> createPaymentTypes(@RequestBody @Valid PaymentTypesRequest request){
        return ResponseEntity.ok(ApiResponse.success(paymentTypesService.createPaymentTypes(request)));
    }

//    @GetMapping("/get-transactions/{id}")
//    public ResponseEntity<ApiResponse<List<TransactionResponse>>> getTransactions(@PathVariable(value = "id") Integer id){
//        return ResponseEntity.ok(ApiResponse.success(transactionService.getAllTransactionsByUserId(id)));
//    }

    @GetMapping("/get-transactions/current-user")
    public ResponseEntity<ApiResponse<?>> getCurrentUserTransactions(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "createdAt") String sortBy) {

        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Long userId = (Long) jwt.getClaims().get("userId");
        Integer userIdInteger = userId.intValue();

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).descending());

        Page<TransactionResponse> transactionsPage = transactionService.getAllTransactionsByUserId(userIdInteger, pageable);

        Map<String, Object> data = new HashMap<>();
        data.put("page", transactionsPage.getNumber());
        data.put("size", transactionsPage.getSize());
        data.put("total", transactionsPage.getTotalPages());
        data.put("transactions", transactionsPage.getContent());

        return ResponseEntity.ok(ApiResponse.success(data));
    }


}
