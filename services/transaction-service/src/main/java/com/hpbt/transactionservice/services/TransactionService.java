package com.hpbt.transactionservice.services;

import com.hpbt.transactionservice.dto.requests.TransactionRequest;
import com.hpbt.transactionservice.dto.requests.UpdateTransactionStatusRequest;
import com.hpbt.transactionservice.dto.responses.TransactionResponse;
import com.hpbt.transactionservice.entities.Transaction;

import java.util.Optional;

public interface TransactionService {
    TransactionResponse createTransaction(TransactionRequest request);
    TransactionResponse updateTransaction(UpdateTransactionStatusRequest request);
    TransactionResponse getTransactionByOrderId(String orderId);
}
