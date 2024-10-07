package com.hpbt.transactionservice.services.impl;

import com.hpbt.transactionservice.dto.requests.TransactionRequest;
import com.hpbt.transactionservice.dto.responses.TransactionResponse;
import com.hpbt.transactionservice.entities.Transaction;
import com.hpbt.transactionservice.mappers.TransactionMapper;
import com.hpbt.transactionservice.repositories.TransactionRepository;
import com.hpbt.transactionservice.services.TransactionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TransactionServiceImpl implements TransactionService {
    TransactionRepository transactionRepository;
    TransactionMapper transactionMapper;


    @Override
    public TransactionResponse createTransaction(TransactionRequest request) {
        Transaction transaction = transactionRepository.save(transactionMapper.toTransaction(request));
        return transactionMapper.toTransactionResponse(transaction);
//        return Optional.empty();
    }
}
