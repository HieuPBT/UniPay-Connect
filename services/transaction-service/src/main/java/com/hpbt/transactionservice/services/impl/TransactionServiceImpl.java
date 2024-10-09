package com.hpbt.transactionservice.services.impl;

import com.hpbt.transactionservice.clients.UserServiceClient;
import com.hpbt.transactionservice.dto.requests.TransactionRequest;
import com.hpbt.transactionservice.dto.requests.UpdateTransactionStatusRequest;
import com.hpbt.transactionservice.dto.responses.ApiResponse;
import com.hpbt.transactionservice.dto.responses.TransactionResponse;
import com.hpbt.transactionservice.entities.PaymentTypes;
import com.hpbt.transactionservice.entities.Transaction;
import com.hpbt.transactionservice.exceptions.CustomException;
import com.hpbt.transactionservice.mappers.TransactionMapper;
import com.hpbt.transactionservice.repositories.PaymentTypesRepository;
import com.hpbt.transactionservice.repositories.TransactionRepository;
import com.hpbt.transactionservice.services.TransactionService;
import com.hpbt.transactionservice.utils.commons.StatusCode;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TransactionServiceImpl implements TransactionService {
    TransactionRepository transactionRepository;
    PaymentTypesRepository paymentTypesRepository;
    TransactionMapper transactionMapper;
    UserServiceClient userServiceClient;


    @Override
    public TransactionResponse createTransaction(TransactionRequest request) {
        ResponseEntity<ApiResponse<Boolean>> response = userServiceClient.isValidUser(request.userId());

        if(response == null || !Boolean.TRUE.equals(Objects.requireNonNull(response.getBody()).getResult())){
            throw new CustomException(StatusCode.USER_NOT_FOUND, StatusCode.USER_NOT_FOUND.getMessage());
        }

        PaymentTypes paymentTypes = paymentTypesRepository.findById(request.paymentTypeId()).orElseThrow(
                () -> new CustomException(StatusCode.BAD_REQUEST, StatusCode.BAD_REQUEST.getMessage())
        );

        Transaction transaction = transactionRepository.save(transactionMapper.toTransaction(request, paymentTypes));
        return transactionMapper.toTransactionResponse(transaction);
//        return Optional.empty();
    }

    @Override
    public TransactionResponse updateTransaction(UpdateTransactionStatusRequest request) {
        Transaction transaction = transactionRepository.findById(request.transactionId()).orElseThrow(
                () -> new CustomException(StatusCode.BAD_REQUEST, StatusCode.BAD_REQUEST.getMessage())
        );

        transaction.setStatus(request.status());

        return transactionMapper.toTransactionResponse(transactionRepository.save(transaction));
    }

    @Override
    public TransactionResponse getTransactionByOrderId(String orderId) {
        System.out.println(orderId);
        Transaction transaction = transactionRepository.findByOrderId(orderId)
                .orElseThrow(() -> new CustomException(
                        StatusCode.BAD_REQUEST, StatusCode.BAD_REQUEST.getMessage()
                ));
        return transactionMapper.toTransactionResponse(transaction);
    }
}
