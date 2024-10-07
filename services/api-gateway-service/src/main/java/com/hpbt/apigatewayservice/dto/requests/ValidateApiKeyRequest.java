package com.hpbt.apigatewayservice.dto.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record ValidateApiKeyRequest(
        @NotNull(message = "ApiKey cannot be null")
        @NotBlank(message = "ApiKey cannot be blank")
        String apiKey
) {
}
