package com.hpbt.userservice.dto.responses;

import com.hpbt.userservice.entities.UserRole;

import java.time.LocalDateTime;

public record UserResponse(
        Integer id,
        String username,
        String fullName,
        String email,
        String phoneNumber,
        String avatar,
        UserRole role,
        Boolean active,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
