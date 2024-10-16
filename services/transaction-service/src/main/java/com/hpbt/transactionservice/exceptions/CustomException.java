package com.hpbt.transactionservice.exceptions;

import com.hpbt.transactionservice.utils.commons.StatusCode;
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
