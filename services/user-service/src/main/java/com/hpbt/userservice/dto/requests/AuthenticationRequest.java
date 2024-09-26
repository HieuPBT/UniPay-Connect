package com.hpbt.userservice.dto.requests;

public record AuthenticationRequest(
        String username,
        String password
) {
}
