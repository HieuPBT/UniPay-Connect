package com.hpbt.paymentgatewayservice.dto.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hpbt.paymentgatewayservice.dto.requests.Status;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record TransactionResponse(
        Integer id,
        Integer userId,
        Long amount,
        String currency,
        Status status,
        String orderId,
        LocalDateTime createdAt
) {
}
