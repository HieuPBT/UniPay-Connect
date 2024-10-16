package com.hpbt.transactionservice.clients;

import com.hpbt.transactionservice.dto.responses.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import reactor.core.publisher.Mono;

@FeignClient(name = "userService", url = "${feign.url.user-service}")
public interface UserServiceClient {
    @PostMapping("/validate-user/{id}")
    ResponseEntity<ApiResponse<Boolean>> isValidUser(@PathVariable(value = "id") int id);
}
