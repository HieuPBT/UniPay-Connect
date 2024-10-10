package com.hpbt.billingservice.commons;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonInclude(NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ApiResponse<T> {
    int code;
    String message;
    T result;

    public static<T> ApiResponse<T> success(T result) {
        return ApiResponse.<T>builder()
                .code(StatusCode.SUCCESS.getCode())
                .message(StatusCode.SUCCESS.getMessage())
                .result(result)
                .build();
    }

    public static ApiResponse<Void> success() {
        return ApiResponse.<Void>builder()
                .code(StatusCode.SUCCESS.getCode())
                .message(StatusCode.SUCCESS.getMessage())
                .build();
    }

    public static ApiResponse<Void> error(int code, String message){
        return ApiResponse.<Void>builder()
                .code(code)
                .message(message)
                .build();
    }
}
