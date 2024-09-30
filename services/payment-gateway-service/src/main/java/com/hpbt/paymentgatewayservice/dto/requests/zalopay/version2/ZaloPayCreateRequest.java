package com.hpbt.paymentgatewayservice.dto.requests.zalopay.version2;

import jakarta.validation.constraints.*;

public record ZaloPayCreateRequest(
        @NotNull(message = "app_id cannot be null")
        int app_id,

        @NotNull(message = "accessKey cannot be null")
        @NotBlank(message = "accessKey cannot be blank")
        String accessKey,

        @NotNull(message = "secretKey cannot be null")
        @NotBlank(message = "secretKey cannot be blank")
        String secretKey,

        @NotNull(message = "app_user cannot be null")
        @NotBlank(message = "app_user cannot be blank")
        String app_user,

        @NotNull(message = "Amount cannot be null")
        @Min(value = 1, message = "Amount must be greater than 0")
        long amount,

        @NotNull(message = "CallbackUrl cannot be null")
        @NotBlank(message = "CallbackUrl cannot be blank")
        @Size(max = 200, message = "CallbackUrl cannot exceed 200 characters")
        @Pattern(regexp = "^(https?|ftp)://[^\\s/$.?#].[^\\s]*$", message = "CallbackUrl must be a valid URL")
        String callbackUrl,

        String description,

        String item,

        String embed_data,

        @NotNull(message = "bank_code cannot be null")
        @NotBlank(message = "bank_code cannot be blank")
        String bank_code,

        @NotNull(message = "ApiKey cannot be null")
        @NotBlank(message = "ApiKey cannot be blank")
        @Size(min = 10, max = 100, message = "ApiKey must be between 10 and 100 characters")
        String apiKey
) {
}
