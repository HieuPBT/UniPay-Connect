package com.hpbt.userservice.utils.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserExistException extends RuntimeException {
    public UserExistException(String message) {
        super(message);
    }
}
