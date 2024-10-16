package com.hpbt.transactionservice.services.impl;

import com.hpbt.transactionservice.dto.requests.PaymentTypesRequest;
import com.hpbt.transactionservice.dto.responses.PaymentTypesResponse;
import com.hpbt.transactionservice.entities.PaymentTypes;
import com.hpbt.transactionservice.mappers.PaymentTypesMapper;
import com.hpbt.transactionservice.repositories.PaymentTypesRepository;
import com.hpbt.transactionservice.services.PaymentTypesService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PaymentTypesServiceImpl implements PaymentTypesService {
    PaymentTypesRepository paymentTypesRepository;
    PaymentTypesMapper paymentTypesMapper;

    @Override
    public PaymentTypesResponse createPaymentTypes(PaymentTypesRequest paymentTypesRequest) {
        PaymentTypes paymentTypes = paymentTypesRepository.save(paymentTypesMapper.toPaymentTypes(paymentTypesRequest));

        return paymentTypesMapper.toPaymentTypesResponse(paymentTypes);
    }
}
