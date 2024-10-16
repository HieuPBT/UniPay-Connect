package com.hpbt.userservice.controllers;

import com.hpbt.userservice.dto.requests.UserRequest;
import com.hpbt.userservice.dto.requests.ValidateApiKeyRequest;
import com.hpbt.userservice.dto.responses.ApiResponse;
import com.hpbt.userservice.dto.responses.UserResponse;
import com.hpbt.userservice.dto.responses.ValidateAccessKeyResponse;
import com.hpbt.userservice.security.CustomUserDetailService;
import com.hpbt.userservice.security.CustomUserDetails;
import com.hpbt.userservice.services.AccessKeyService;
import com.hpbt.userservice.services.JwtService;
import com.hpbt.userservice.services.UserService;
import com.nimbusds.jose.JOSEException;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/internal/user")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class InternalController {
    UserService userService;
    AccessKeyService accessKeyService;
    JwtService jwtService;
    CustomUserDetailService customUserDetailService;

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

    @PostMapping("/validate-jwt-token")
    public ResponseEntity<ApiResponse<ValidateAccessKeyResponse>> validateJwtToken(@RequestBody @Valid ValidateApiKeyRequest request) throws ParseException, JOSEException {
        String username = jwtService.extractUsername(request.apiKey());
        CustomUserDetails customUserDetails = (CustomUserDetails) customUserDetailService.loadUserByUsername(username);
        Boolean isValid = jwtService.validateJwtToken(request.apiKey(), customUserDetails);

        return ResponseEntity.ok(ApiResponse.success(ValidateAccessKeyResponse.builder()
                        .isValid(isValid)
                .build()));
    }
}
