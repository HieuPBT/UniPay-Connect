package com.hpbt.paymentgatewayservice.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "userService", url = "${feign.url.user-service}")
public interface UserServiceClient {
    @PostMapping("/validate-api-key")
    ResponseEntity<Boolean> validateApiKey(@RequestParam("apiKey") String apiKey);
}
