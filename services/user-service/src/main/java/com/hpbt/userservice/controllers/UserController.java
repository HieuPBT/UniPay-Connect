package com.hpbt.userservice.controllers;

import com.hpbt.userservice.dto.requests.*;
import com.hpbt.userservice.dto.responses.AccessKeyResponse;
import com.hpbt.userservice.dto.responses.ApiResponse;
import com.hpbt.userservice.dto.responses.UserResponse;
import com.hpbt.userservice.dto.responses.ValidateAccessKeyResponse;
import com.hpbt.userservice.exceptions.StatusCode;
import com.hpbt.userservice.mappers.UserMapper;
import com.hpbt.userservice.services.AccessKeyService;
import com.hpbt.userservice.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.hibernate5.SpringSessionContext;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;
    private final AccessKeyService accessKeyService;
    private final UserMapper userMapper;

    @RequestMapping("/hello")
//    @PreAuthorize("hasRole('ADMIN')")
    public String hello() {
        return "Hello World";
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@ModelAttribute @Valid UserRequest request) {
        UserResponse user = userService.createUser(request);

        return ResponseEntity.ok(ApiResponse.builder()
                .code(StatusCode.CREATED.getCode())
                .message(StatusCode.CREATED.getMessage())
                .result(user).build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable(value = "id") int id ){
        UserResponse user = userService.getUserById(id);

        return ResponseEntity.ok(ApiResponse.success(user));
    }

//    @PreAuthorize("hasRole('MERCHANT')")
    @GetMapping("/currentUser")
    public ResponseEntity<?> getCurrentUser(Principal principal){
        UserResponse userResponse = userService.getUserByUsername(principal.getName());

        return ResponseEntity.ok(ApiResponse.success(userResponse));
    }

    @PostMapping("/create-api-key")
    public ResponseEntity<?> createAccessKey() {
        AccessKeyResponse accessKeyResponse = accessKeyService.createAccessKey();

        return ResponseEntity.ok(ApiResponse.success(accessKeyResponse));
    }

    @PostMapping("/revoke-api-key")
    public ResponseEntity<?> revokeApiKey(@RequestBody @Valid RevokedAccessKeyRequest request) {
        AccessKeyResponse accessKeyResponse = accessKeyService.revokeAccessKey(request);

        return ResponseEntity.ok(ApiResponse.success(accessKeyResponse));
    }


    @PostMapping("/validate-api-key")
    public ResponseEntity<ApiResponse<ValidateAccessKeyResponse>> validateApiKey(@RequestBody @Valid ValidateApiKeyRequest request) {
        Boolean isValid = accessKeyService.validateAccessKey(request.apiKey());

        return ResponseEntity.ok(ApiResponse.success(ValidateAccessKeyResponse.builder()
                .isValid(isValid).build()));
    }
}

