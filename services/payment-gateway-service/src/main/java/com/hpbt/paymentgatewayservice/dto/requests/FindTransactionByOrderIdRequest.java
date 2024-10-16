package com.hpbt.paymentgatewayservice.dto.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record FindTransactionByOrderIdRequest(
        @NotNull(message = "orderId cannot be null")
        @NotBlank(message = "orderId cannot be blank")
        String orderId
) {
}
