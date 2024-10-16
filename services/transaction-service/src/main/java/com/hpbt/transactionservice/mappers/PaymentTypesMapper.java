package com.hpbt.transactionservice.mappers;

import com.hpbt.transactionservice.dto.requests.PaymentTypesRequest;
import com.hpbt.transactionservice.dto.responses.PaymentTypesResponse;
import com.hpbt.transactionservice.entities.PaymentTypes;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PaymentTypesMapper {
    public PaymentTypes toPaymentTypes(PaymentTypesRequest request) {
        return PaymentTypes.builder()
                .name(request.name())
                .description(request.description())
                .isActive(true)
                .createdAt(LocalDateTime.now())
                .build();
    }

    public PaymentTypesResponse toPaymentTypesResponse(PaymentTypes paymentTypes) {
        return new PaymentTypesResponse(
                paymentTypes.getId(),
                paymentTypes.getName(),
                paymentTypes.getDescription(),
                paymentTypes.isActive(),
                paymentTypes.getCreatedAt()
        );
    }
}
