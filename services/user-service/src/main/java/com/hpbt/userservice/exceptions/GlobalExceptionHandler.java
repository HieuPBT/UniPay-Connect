package com.hpbt.userservice.exceptions;

import com.hpbt.userservice.dto.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserExistException.class)
    public ResponseEntity<ApiResponse<Void>> userExistException(UserExistException userExistException) {
//        ApiResponse apiRe = new ApiResponse<>(1000, userExistException.getMessage(), null);
//        ApiResponse apiResponse = new ApiResponse<>();
        StatusCode statusCode = StatusCode.USER_EXISTED;
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.<Void>builder()
                .code(statusCode.getCode())
                .message(statusCode.getMessage())
                .build());
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ApiResponse<Void>> customException(CustomException customException) {
        StatusCode statusCode = customException.getStatusCode();
//        ApiResponse apiResponse = new ApiResponse<>();
//        apiResponse.setCode(statusCode.getCode());
//        apiResponse.setMessage(customException.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.<Void>builder()
                .code(statusCode.getCode())
                .message(statusCode.getMessage())
                .build());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Void>> methodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException) {
//        ApiResponse apiResponse = new ApiResponse<>();
//
//        apiResponse.setCode(StatusCode.BAD_REQUEST.getCode());
//        apiResponse.setMessage(methodArgumentNotValidException.getFieldError().getDefaultMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.<Void>builder()
                .code(StatusCode.BAD_REQUEST.getCode())
                .message(Objects.requireNonNull(methodArgumentNotValidException.getFieldError()).getDefaultMessage())
                .build());
    }
}
