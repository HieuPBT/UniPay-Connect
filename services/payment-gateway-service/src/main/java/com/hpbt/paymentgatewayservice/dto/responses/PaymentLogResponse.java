package com.hpbt.paymentgatewayservice.dto.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hpbt.paymentgatewayservice.entities.Status;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record PaymentLogResponse(
        Integer id,
        String requestUrl,
        Status status,
        String context,
        Integer transactionId,
        LocalDateTime createdAt
) {
}
