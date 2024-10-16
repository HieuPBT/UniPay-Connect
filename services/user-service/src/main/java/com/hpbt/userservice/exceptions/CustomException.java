package com.hpbt.userservice.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomException extends RuntimeException {
    private final StatusCode statusCode;
    public CustomException(StatusCode statusCode, String description) {
        super(description);
        this.statusCode = statusCode;
    }
}
