package com.hpbt.transactionservice.mappers;

import com.hpbt.transactionservice.dto.requests.TransactionRequest;
import com.hpbt.transactionservice.dto.responses.TransactionResponse;
import com.hpbt.transactionservice.entities.PaymentTypes;
import com.hpbt.transactionservice.entities.Transaction;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TransactionMapper {
    public TransactionResponse toTransactionResponse(Transaction transaction) {
        return new TransactionResponse(
                transaction.getId(),
                transaction.getUserId(),
                transaction.getAmount(),
                transaction.getCurrency(),
                transaction.getStatus(),
                transaction.getOrderId(),
                transaction.getCreatedAt()
        );
    }

    public Transaction toTransaction(TransactionRequest request, PaymentTypes paymentTypes) {
        return Transaction.builder()
                .amount(request.amount())
                .currency(request.currency())
                .orderId(request.orderId())
                .userId(request.userId())
                .status(request.status())
                .paymentType(paymentTypes)
                .createdAt(LocalDateTime.now())
                .build();
    }
}
