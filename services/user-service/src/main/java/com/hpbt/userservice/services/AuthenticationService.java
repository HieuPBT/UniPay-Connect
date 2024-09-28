package com.hpbt.userservice.services;

import com.hpbt.userservice.dto.requests.AuthenticationRequest;
import com.hpbt.userservice.dto.responses.AuthenticationResponse;
import com.hpbt.userservice.entities.User;
import com.hpbt.userservice.exceptions.CustomException;
import com.hpbt.userservice.exceptions.StatusCode;
import com.hpbt.userservice.mappers.UserMapper;
import com.hpbt.userservice.repositories.UserRepository;
import com.hpbt.userservice.security.CustomUserDetailService;
import com.hpbt.userservice.security.CustomUserDetails;
import com.nimbusds.jose.JOSEException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationService {
    private final UserRepository userRepository;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    private final UserMapper userMapper;

    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) throws JOSEException {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.username(),
                        authenticationRequest.password()
                )
        );

        User user = userRepository.findByUsername(authenticationRequest.username()).orElseThrow(
                () -> new CustomException(StatusCode.USER_NOT_FOUND, "User with usename " + authenticationRequest.username() + " not found")
        );

        var jwtToken = jwtService.generateJwtToken(user);
        return userMapper.toAuthenticationResponse(jwtToken);
//        return null;
    }
}
