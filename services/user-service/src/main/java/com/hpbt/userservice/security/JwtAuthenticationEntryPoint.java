package com.hpbt.userservice.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hpbt.userservice.dto.responses.ApiResponse;
import com.hpbt.userservice.exceptions.StatusCode;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");

        StatusCode statusCode = StatusCode.UNAUTHENTICATED;
        ApiResponse<String> apiResponse = ApiResponse.<String>builder()
                .code(statusCode.getCode())
                .message(statusCode.getMessage())
                .build();

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonResponse = objectMapper.writeValueAsString(apiResponse);

        response.getWriter().write(jsonResponse);
    }
}
