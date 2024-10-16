package com.hpbt.userservice.dto.requests;

import jakarta.validation.constraints.NotNull;

public record RevokedAccessKeyRequest(
        @NotNull(message = "id can not be null")
        int id
) {
}
