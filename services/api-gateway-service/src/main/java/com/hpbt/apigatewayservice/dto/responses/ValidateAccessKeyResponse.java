package com.hpbt.apigatewayservice.dto.responses;

import lombok.Builder;

@Builder
public record ValidateAccessKeyResponse(
        Boolean isValid
) {
}
