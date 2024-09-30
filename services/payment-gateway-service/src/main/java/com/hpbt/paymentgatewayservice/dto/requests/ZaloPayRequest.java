package com.hpbt.paymentgatewayservice.dto.requests;

public record ZaloPayRequest(
        int app_id,
        String app_user,
        String app_trans_id,
        long app_time,
        long amount,
        String item,
        String description,
        String embed_data,
        String bank_code,
        String mac
) {
}
