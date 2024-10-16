package com.hpbt.transactionservice.utils.commons;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum StatusCode {
    // Success codes
    SUCCESS(2000, "Success", HttpStatus.OK),
    CREATED(2002, "User created", HttpStatus.CREATED),

    // Client error codes
    BAD_REQUEST(1000, "Wrong format", HttpStatus.BAD_REQUEST),
    USER_EXISTED(1001, "User existed", HttpStatus.CONFLICT),
    USER_NOT_FOUND(1002, "User Not Found", HttpStatus.NOT_FOUND),
    ACCESS_KEY_NOT_FOUND(1003, "Access Key Not Found", HttpStatus.NOT_FOUND),
    PASSWORD_TOO_WEAK(1004, "Password too weak", HttpStatus.BAD_REQUEST),
    EMAIL_NOT_VERIFIED(1005, "Email not verified", HttpStatus.FORBIDDEN),

    // Authentication and authorization error codes
    UNAUTHENTICATED(4000, "Unauthorized", HttpStatus.UNAUTHORIZED),
    INVALID_TOKEN(4001, "Invalid token", HttpStatus.UNAUTHORIZED),
    FORBIDDEN(4002, "Forbidden", HttpStatus.FORBIDDEN),

    // Server error codes
    FAILED(2001, "Failed", HttpStatus.INTERNAL_SERVER_ERROR),
    INTERNAL(5000, "Internal error", HttpStatus.INTERNAL_SERVER_ERROR),
    DATABASE_ERROR(5001, "Database error", HttpStatus.INTERNAL_SERVER_ERROR),
    NOT_IMPLEMENTED(5010, "Not implemented", HttpStatus.NOT_IMPLEMENTED),

    // Other error codes
    RESOURCE_LOCKED(4230, "Resource is locked", HttpStatus.LOCKED),
    TOO_MANY_REQUESTS(4290, "Too many requests", HttpStatus.TOO_MANY_REQUESTS),
    SERVICE_UNAVAILABLE(5030, "Service temporarily unavailable", HttpStatus.SERVICE_UNAVAILABLE);

    int code;
    String message;
    HttpStatus statusCode;
}


