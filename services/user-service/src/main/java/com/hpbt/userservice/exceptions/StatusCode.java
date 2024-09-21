package com.hpbt.userservice.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
public enum StatusCode {
    BAD_REQUEST(1000, "Wrong format"),
    USER_EXISTED(1001, "User existed"),
    USER_NOT_FOUND(1002, "User Not Found"),
    SUCCESS(2000, "Success"),
    CREATED(2001, "User created"),
    UNAUTHORIZED(4000, "Unauthorized"),
    FORBIDDEN(4001, "Forbidden"),
    INTERNAL(5000, "Internal error");

    private int code;
    private String message;
}
