package com.hpbt.userservice.exceptions;

import com.hpbt.userservice.dto.responses.ApiResponse;
import com.hpbt.userservice.mappers.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {
    private final UserMapper userMapper;

    @ExceptionHandler(UserExistException.class)
    public ResponseEntity<ApiResponse> userExistException(UserExistException userExistException) {
//        ApiResponse apiRe = new ApiResponse<>(1000, userExistException.getMessage(), null);
        ApiResponse apiResponse = new ApiResponse<>();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ApiResponse> customException(CustomException customException) {
        StatusCode statusCode = customException.getStatusCode();
        ApiResponse apiResponse = new ApiResponse<>();
        apiResponse.setCode(statusCode.getCode());
        apiResponse.setMessage(customException.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse> methodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException) {
        ApiResponse apiResponse = new ApiResponse<>();

        apiResponse.setCode(StatusCode.BAD_REQUEST.getCode());
        apiResponse.setMessage(methodArgumentNotValidException.getFieldError().getDefaultMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
    }
}
