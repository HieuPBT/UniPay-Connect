package com.hpbt.userservice.controllers;

import com.hpbt.userservice.dto.requests.AccessKeyRequest;
import com.hpbt.userservice.dto.requests.UpdateUserRequest;
import com.hpbt.userservice.dto.requests.UserRequest;
import com.hpbt.userservice.dto.responses.AccessKeyResponse;
import com.hpbt.userservice.dto.responses.ApiResponse;
import com.hpbt.userservice.dto.responses.UserResponse;
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
        ApiResponse apiResponse = new ApiResponse(StatusCode.CREATED.getCode(), StatusCode.CREATED.getMessage(), user);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable(value = "id") int id ){
        UserResponse user = userService.getUserById(id);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode(StatusCode.SUCCESS.getCode());
        apiResponse.setMessage(StatusCode.SUCCESS.getMessage());
        apiResponse.setResult(user);

        return ResponseEntity.ok(apiResponse);
    }

//    @PreAuthorize("hasRole('MERCHANT')")
    @GetMapping("/currentUser")
    public ResponseEntity<?> getCurrentUser(Principal principal){
        UserResponse userResponse = userService.getUserByUsername(principal.getName());

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode(StatusCode.SUCCESS.getCode());
        apiResponse.setMessage(StatusCode.SUCCESS.getMessage());
        apiResponse.setResult(userResponse);

        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("/create-access-key")
    public ResponseEntity<?> createAccessKey(@Valid @RequestBody AccessKeyRequest request) {
        AccessKeyResponse accessKeyResponse = accessKeyService.createAccessKey(request);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode(StatusCode.SUCCESS.getCode());
        apiResponse.setMessage(StatusCode.SUCCESS.getMessage());
        apiResponse.setResult(accessKeyResponse);

        return ResponseEntity.ok(apiResponse);
    }


}

