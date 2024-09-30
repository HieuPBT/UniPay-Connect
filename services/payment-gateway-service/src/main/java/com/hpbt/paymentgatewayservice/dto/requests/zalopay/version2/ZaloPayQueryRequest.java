package com.hpbt.paymentgatewayservice.dto.requests.zalopay.version2;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ZaloPayQueryRequest(
        @NotNull(message = "app_id cannot be null")
        int app_id,

        @NotNull(message = "app_trans_id cannot be null")
        @NotBlank(message = "app_trans_id cannot be blank")
        String app_trans_id,

        @NotNull(message = "accessKey cannot be null")
        @NotBlank(message = "accessKey cannot be blank")
        String accessKey,

        @NotNull(message = "secretKey cannot be null")
        @NotBlank(message = "secretKey cannot be blank")
        String secretKey,

        @NotNull(message = "ApiKey cannot be null")
        @NotBlank(message = "ApiKey cannot be blank")
        @Size(min = 10, max = 100, message = "ApiKey must be between 10 and 100 characters")
        String apiKey
) {
}
