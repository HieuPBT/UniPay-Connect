package com.hpbt.paymentgatewayservice.dto.requests.zalopay.version2;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record ZaloPayQueryRefundRequest(
        @NotNull(message = "app_id cannot be null")
        int app_id,

        @NotNull(message = "m_refund_id cannot be null")
        @NotBlank(message = "m_refund_id cannot be blank")
        @Pattern(regexp = "\\d{6}_\\d+_\\w+", message = "m_refund_id must follow the format: yymmdd_appid_xxxxxxxxxx")
        String m_refund_id,

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
