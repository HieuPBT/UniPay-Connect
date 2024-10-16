package com.hpbt.userservice.mappers;

import com.hpbt.userservice.dto.requests.AccessKeyRequest;
import com.hpbt.userservice.dto.responses.AccessKeyResponse;
import com.hpbt.userservice.entities.AccessKey;
import com.hpbt.userservice.entities.Status;
import com.hpbt.userservice.entities.User;
import com.hpbt.userservice.utils.commons.CommonUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccessKeyMapper {
    private String generateApiKey() {
        return UUID.randomUUID().toString() + "_" +LocalDateTime.now();
    }

    public AccessKey toAccessKey(User user) {
//        if(accessKeyRequest == null) return null;

        return AccessKey.builder()
                .apiKey(CommonUtil.generateApiKey())
                .status(Status.ACTIVE)
                .created_at(LocalDateTime.now())
                .expires_at(LocalDateTime.now().plusDays(30))
                .user(user)
                .build();
    }

    public AccessKeyResponse toAccessKeyResponse(AccessKey accessKey) {
        return new AccessKeyResponse(
                accessKey.getId(),
                accessKey.getApiKey(),
                accessKey.getStatus(),
                accessKey.getExpires_at(),
                accessKey.getRevoked_at(),
                accessKey.getCreated_at()
        );
    }
}
