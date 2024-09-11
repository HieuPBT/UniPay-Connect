package com.hpbt.userservice.utils.handlers;

import com.hpbt.userservice.utils.exceptions.UserExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UserExistException.class)
    public ResponseEntity<String> userExistException(UserExistException userExistException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(userExistException.getMessage());
    }
}
