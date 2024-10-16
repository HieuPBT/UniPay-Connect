package com.hpbt.apigatewayservice.services;

import com.hpbt.apigatewayservice.dto.requests.ValidateApiKeyRequest;
import com.hpbt.apigatewayservice.dto.responses.ApiResponse;
import com.hpbt.apigatewayservice.dto.responses.ValidateAccessKeyResponse;
import com.hpbt.apigatewayservice.repositories.UserServiceClient;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {
    UserServiceClient userServiceClient;

    public Mono<ApiResponse<ValidateAccessKeyResponse>> validateApiKey(String apiKey) {
        return userServiceClient.validateApiKey(ValidateApiKeyRequest.builder().apiKey(apiKey).build());
    }

    public Mono<ApiResponse<ValidateAccessKeyResponse>> validateJwtToken(String apiKey) {
        return userServiceClient.validateJwtToken(ValidateApiKeyRequest.builder().apiKey(apiKey).build());
    }
}