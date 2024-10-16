package com.hpbt.userservice.dto.responses;

import lombok.Builder;

@Builder
public record ValidateAccessKeyResponse(
        Boolean isValid
) {
}
