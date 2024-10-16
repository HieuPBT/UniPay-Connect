package com.hpbt.paymentgatewayservice.dto.responses;

public record UserResponse(
        Integer id,
        String username,
        String fullName,
        String email,
        String phoneNumber,
        String avatar
) {
}
