package com.hpbt.userservice.dto.requests;

import org.springframework.web.multipart.MultipartFile;

public record UserRequest(
        String username,
        String password,
        String email,
        String fullName,
        String phoneNumber,
        MultipartFile avatar
) {
}
