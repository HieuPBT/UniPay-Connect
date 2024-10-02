package com.hpbt.userservice.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hpbt.userservice.dto.responses.ApiResponse;
import com.hpbt.userservice.exceptions.StatusCode;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {


    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType("application/json");
//        throw new AccessDeniedException(.getMessage());
//        response.getWriter().write("{\"error\": \"You do not have permission to access this resource.\"}");
        StatusCode statusCode = StatusCode.FORBIDDEN;
        ApiResponse<String> apiResponse = ApiResponse.<String>builder()
                .code(statusCode.getCode())
                .message(statusCode.getMessage())
                .build();

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonResponse = objectMapper.writeValueAsString(apiResponse);

        response.getWriter().write(jsonResponse);
    }
}
