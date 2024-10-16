package com.hpbt.transactionservice.dto.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record PaymentTypesRequest(
        @NotBlank(message = "name cannot be blank")
        @NotNull(message = "name cannot be null")
        String name,

        String description
) {
}
