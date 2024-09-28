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
@RequestMapping("/api/v1")
public class AuthenticationController {
    private final UserService userService;

    private final AuthenticationService authenticationService;

    private AuthenticationManager authenticationManager;

//    @Autowired
//    private UserDetailsService userDetailsService;

//    @PostMapping("/login")
//    public ResponseEntity<ApiResponse> login(@RequestBody AuthenticationRequest authenticationRequest) {
//        try{
//            Authentication authentication = authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(authenticationRequest.username(), authenticationRequest.password())
//            );
//
//            SecurityContextHolder.getContext().setAuthentication(authentication);
////            UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.username());
//
//            String token = JwtAuthenticationProvider.generateToken(userDetails.getUsername());
//
//            ApiResponse apiResponse = new ApiResponse();
//            apiResponse.setCode(StatusCode.SUCCESS.getCode());
//            apiResponse.setMessage("Successfully logged in");
//            apiResponse.setResult(new AuthenticationResponse(token));
//
//            return ResponseEntity.ok(apiResponse);
//        } catch(AuthenticationException e){
//            return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                    .body(new ApiResponse<>(StatusCode.USER_NOT_FOUND.getCode(), e.getMessage()));
//        } catch(JOSEException e){
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body(new ApiResponse<>(StatusCode.INTERNAL.getCode(), e.getMessage()));
//        }
//
//    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> authenticate(@RequestBody @Valid AuthenticationRequest authenticationRequest) throws JOSEException {
        AuthenticationResponse authenticationResponse = authenticationService.authenticate(authenticationRequest);

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode(StatusCode.SUCCESS.getCode());
        apiResponse.setMessage(StatusCode.SUCCESS.getMessage());
        apiResponse.setResult(authenticationResponse);

        return ResponseEntity.ok(apiResponse);
    }
}
