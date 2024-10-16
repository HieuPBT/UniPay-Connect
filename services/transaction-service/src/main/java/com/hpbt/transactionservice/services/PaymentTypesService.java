package com.hpbt.transactionservice.services;

import com.hpbt.transactionservice.dto.requests.PaymentTypesRequest;
import com.hpbt.transactionservice.dto.responses.PaymentTypesResponse;

public interface PaymentTypesService {
    PaymentTypesResponse createPaymentTypes(PaymentTypesRequest paymentTypesRequest);
}
