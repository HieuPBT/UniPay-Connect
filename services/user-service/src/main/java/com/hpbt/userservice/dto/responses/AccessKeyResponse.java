package com.hpbt.userservice.dto.responses;

import com.hpbt.userservice.entities.Status;

import java.time.LocalDateTime;

public record AccessKeyResponse(
        Integer id,
        String apiKey,
        Status status,
        LocalDateTime expires_at,
        LocalDateTime revoked_at
) {
}
