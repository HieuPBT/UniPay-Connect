package com.hpbt.userservice.dto.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hpbt.userservice.entities.Status;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record AccessKeyResponse(
        Integer id,
        String apiKey,
        Status status,
        LocalDateTime expires_at,
        LocalDateTime revoked_at,
        LocalDateTime created_at
) {
}
