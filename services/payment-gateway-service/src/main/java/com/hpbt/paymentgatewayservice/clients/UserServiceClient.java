package com.hpbt.paymentgatewayservice.clients;

import com.hpbt.paymentgatewayservice.dto.requests.momo.ValidateApiKeyRequest;
import com.hpbt.paymentgatewayservice.dto.responses.ApiResponse;
import com.hpbt.paymentgatewayservice.dto.responses.UserResponse;
import com.hpbt.paymentgatewayservice.dto.responses.ValidateAccessKeyResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "userService", url = "${feign.url.user-service}")
public interface UserServiceClient {
    @PostMapping("/validate-api-key")
    ResponseEntity<ApiResponse<ValidateAccessKeyResponse>> validateApiKey(@RequestBody ValidateApiKeyRequest request);

    @PostMapping("/find-user-by-api-key")
    ResponseEntity<ApiResponse<UserResponse>> findUserByApiKey(@RequestBody ValidateApiKeyRequest request);
}
