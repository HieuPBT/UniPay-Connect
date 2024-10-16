package com.hpbt.notificationservice.entities;

public record UserRegisterInfo(
        Integer id,
        String username,
        String fullName,
        String email,
        String phoneNumber,
        String avatar
) {
}
