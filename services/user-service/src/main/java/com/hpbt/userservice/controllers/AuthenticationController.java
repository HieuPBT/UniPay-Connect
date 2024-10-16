package com.hpbt.userservice.controllers;

import com.hpbt.userservice.dto.requests.AuthenticationRequest;
import com.hpbt.userservice.dto.responses.ApiResponse;
import com.hpbt.userservice.dto.responses.AuthenticationResponse;
import com.hpbt.userservice.exceptions.StatusCode;
import com.hpbt.userservice.services.AuthenticationService;
import com.hpbt.userservice.services.UserService;
import com.hpbt.userservice.security.JwtAuthenticationProvider;
import com.nimbusds.jose.JOSEException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/authentication")
public class AuthenticationController {
    private final UserService userService;

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<?>> authenticate(@RequestBody @Valid AuthenticationRequest authenticationRequest) throws JOSEException {
        AuthenticationResponse authenticationResponse = authenticationService.authenticate(authenticationRequest);

//        ApiResponse apiResponse = new ApiResponse();
//        apiResponse.setCode(StatusCode.SUCCESS.getCode());
//        apiResponse.setMessage(StatusCode.SUCCESS.getMessage());
//        apiResponse.setResult(authenticationResponse);

        return ResponseEntity.ok(ApiResponse.success(authenticationResponse));
    }
}
