package com.hpbt.event;

import lombok.Builder;

@Builder
public record UserRegisterInfo(
        Integer id,
        String username,
        String fullName,
        String email,
        String phoneNumber,
        String avatar
) {
}
