package com.hpbt.userservice.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserExistException extends RuntimeException {
    public UserExistException(String message) {
        super(message);
    }
}
