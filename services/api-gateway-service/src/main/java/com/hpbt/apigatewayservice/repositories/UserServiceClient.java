package com.hpbt.apigatewayservice.repositories;

import com.hpbt.apigatewayservice.dto.requests.ValidateApiKeyRequest;
import com.hpbt.apigatewayservice.dto.responses.ApiResponse;
import com.hpbt.apigatewayservice.dto.responses.ValidateAccessKeyResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.PostExchange;
import reactor.core.publisher.Mono;

public interface UserServiceClient {
    @PostExchange(url = "/api/v1/user/validate-api-key", contentType = MediaType.APPLICATION_JSON_VALUE)
    Mono<ApiResponse<ValidateAccessKeyResponse>> validateApiKey(@RequestBody ValidateApiKeyRequest validateApiKeyRequest);
}
