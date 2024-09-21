package com.hpbt.userservice.dto.requests;

import jakarta.validation.constraints.*;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.web.multipart.MultipartFile;

public record UserRequest(
        @Size(min = 3, message = "Username must be at least 3 character")
        @NotBlank(message = "Username cannot be blank")
        @NotNull(message = "Username cannot be null")
        String username,

        @Pattern(
                regexp = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&#])[A-Za-z\\d@$!%*?&#]{8,}$",
                message = "Password must be at least 8 characters long, contain at least 1 uppercase letter, 1 number, and 1 special character"
        )
        @NotBlank(message = "Password cannot be blank")
        @NotNull(message = "Password cannot be null")
        String password,

        @NotNull(message = "Email cannot be null")
        @NotBlank(message = "Email cannot be blank")
        @Email(message = "Must be a valid email address")
        String email,

        @NotNull(message = "Full name cannot be null")
        @NotBlank(message = "Full name cannot be blank")
        @Size(min = 6, message = "Full name must be at least 6 character")
        String fullName,

        @Pattern(
                regexp = "^(\\+\\d{1,3}[- ]?)?\\d{10}$",
                message = "Phone number must be valid (e.g. +1234567890 or 1234567890)"
        )
        @NotBlank(message = "Phone number cannot be blank")
        @NotNull(message = "Phone number cannot be null")
        String phoneNumber,

        MultipartFile avatar
) {
}
