package com.hpbt.userservice.dto.responses;

import com.hpbt.userservice.entities.UserRole;

public record UserResponse(
        Integer id,
        String username,
        String fullName,
        String email,
        String phoneNumber,
        String avatar,
        UserRole role
) {
}
