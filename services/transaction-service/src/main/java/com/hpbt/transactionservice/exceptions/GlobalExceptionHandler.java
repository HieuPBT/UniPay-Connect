package com.hpbt.transactionservice.exceptions;


import com.hpbt.transactionservice.dto.responses.ApiResponse;
import com.hpbt.transactionservice.utils.commons.StatusCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

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
