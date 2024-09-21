package com.hpbt.userservice.dto.requests;

public record AccessKeyRequest(
    String apiKey,
    Integer userId
) {
}
