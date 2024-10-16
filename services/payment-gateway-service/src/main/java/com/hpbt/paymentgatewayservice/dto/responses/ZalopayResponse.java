package com.hpbt.paymentgatewayservice.dto.responses;

public record ZalopayResponse(
        int return_code,
        String return_message,
        int sub_return_code,
        String zp_trans_tokenn,
        String order_url,
        String order_token,
        String qr_code
) {
}
