package com.hpbt.userservice.dto.responses;

public record UserResponse(
        Integer id,
        String username,
        String fullName,
        String email,
        String phoneNumber,
        String avatar
) {
}
