package com.hpbt.paymentgatewayservice.dto.responses;

import lombok.Builder;

@Builder
public record ValidateAccessKeyResponse(
        Boolean isValid
) {
}
