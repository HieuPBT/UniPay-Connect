package com.hpbt.paymentgatewayservice.dto.requests.zalopay.version2;

import jakarta.validation.constraints.*;

public record ZaloPayRefundRequest(
        @NotNull(message = "app_id cannot be null")
        int app_id,

        @NotNull(message = "zp_trans_id cannot be null")
        @NotBlank(message = "zp_trans_i cannot be blank")
        @Size(max = 15, message = "zp_trans_id must not exceed 15 characters")
        String zp_trans_id,

        @NotNull(message = "amount cannot be null")
        @Positive(message = "amount must be a positive number")
        long amount,

        @Positive(message = "refund_fee_amount must be a positive number")
        Long refund_fee_amount,

        @NotNull(message = "description cannot be null")
        @NotBlank(message = "description cannot be blank")
        @Size(max = 100, message = "description must not exceed 100 characters")
        String description,

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
