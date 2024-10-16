package com.hpbt.userservice.dto.requests;

public record UpdateUserRequest(
        String fullName,
        String email,
        String phoneNumber,
        String avatar,
        String password

) {
}
