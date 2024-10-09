package com.hpbt.userservice.controllers;

import com.hpbt.userservice.dto.requests.UserRequest;
import com.hpbt.userservice.dto.requests.ValidateApiKeyRequest;
import com.hpbt.userservice.dto.responses.ApiResponse;
import com.hpbt.userservice.dto.responses.UserResponse;
import com.hpbt.userservice.dto.responses.ValidateAccessKeyResponse;
import com.hpbt.userservice.services.AccessKeyService;
import com.hpbt.userservice.services.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/internal/v1/user")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class InternalController {
    UserService userService;
    AccessKeyService accessKeyService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable(value = "id") int id ){
        UserResponse user = userService.getUserById(id);

        return ResponseEntity.ok(ApiResponse.success(user));
    }

    @PostMapping("/validate-user/{id}")
    public ResponseEntity<ApiResponse<Boolean>> isValidUser(@PathVariable(value = "id") int id){
        Boolean isValid = userService.isUserExist(id);

        return ResponseEntity.ok(ApiResponse.success(isValid));
    }

    @PostMapping("/validate-api-key")
    public ResponseEntity<ApiResponse<ValidateAccessKeyResponse>> validateApiKey(@RequestBody @Valid ValidateApiKeyRequest request) {
        Boolean isValid = accessKeyService.validateAccessKey(request.apiKey());

        return ResponseEntity.ok(ApiResponse.success(ValidateAccessKeyResponse.builder()
                .isValid(isValid).build()));
    }

    @PostMapping(value = "/find-user-by-api-key")
    public ResponseEntity<ApiResponse<UserResponse>> findUserByApiKey(@RequestBody ValidateApiKeyRequest request){
        return ResponseEntity.ok(ApiResponse.success(userService.findUserByApiKey(request)));
    }
}
