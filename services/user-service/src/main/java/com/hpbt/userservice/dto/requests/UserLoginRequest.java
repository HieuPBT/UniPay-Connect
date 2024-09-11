package com.hpbt.userservice.dto.requests;

public record UserLoginRequest(
        String username,
        String password
) {
}
