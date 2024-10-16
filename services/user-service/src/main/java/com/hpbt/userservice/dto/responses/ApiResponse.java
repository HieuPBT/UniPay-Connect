package com.hpbt.userservice.dto.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hpbt.userservice.exceptions.StatusCode;
import lombok.*;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonInclude(NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ApiResponse<T> {
    private int code;
    private String message;
    private T result;

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
