package com.hpbt.paymentgatewayservice.exceptions;

import com.hpbt.paymentgatewayservice.utils.commons.StatusCode;
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
