package com.hpbt.userservice.services.impl;

import com.hpbt.userservice.dto.requests.AccessKeyRequest;
import com.hpbt.userservice.dto.requests.RevokedAccessKeyRequest;
import com.hpbt.userservice.dto.responses.AccessKeyResponse;
import com.hpbt.userservice.entities.AccessKey;
import com.hpbt.userservice.entities.Status;
import com.hpbt.userservice.entities.User;
import com.hpbt.userservice.exceptions.CustomException;
import com.hpbt.userservice.exceptions.StatusCode;
import com.hpbt.userservice.mappers.AccessKeyMapper;
import com.hpbt.userservice.repositories.AccessKeyRepository;
import com.hpbt.userservice.repositories.UserRepository;
import com.hpbt.userservice.services.AccessKeyService;
import com.hpbt.userservice.utils.commons.CommonUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
public class AccessKeyImpl implements AccessKeyService {
    private final AccessKeyRepository accessKeyRepository;
    private final UserRepository userRepository;
    private final AccessKeyMapper accessKeyMapper;

    @Override
    public AccessKeyResponse createAccessKey() {
        Integer userId = CommonUtil.getCurrentUserId();
        if(userId == null) {
            throw new CustomException(StatusCode.UNAUTHENTICATED, StatusCode.UNAUTHENTICATED.getMessage());
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(StatusCode.USER_NOT_FOUND, "User with id " + userId + " does not exist"));

        var accessKey = accessKeyRepository.save(accessKeyMapper.toAccessKey(user));

        return accessKeyMapper.toAccessKeyResponse(accessKey);
    }

    @Override
    public AccessKeyResponse revokeAccessKey(RevokedAccessKeyRequest request) {
        Integer userId = CommonUtil.getCurrentUserId();
        if(userId == null) {
            throw new CustomException(StatusCode.UNAUTHENTICATED, StatusCode.UNAUTHENTICATED.getMessage());
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(StatusCode.USER_NOT_FOUND, "User with id " + userId + " does not exist"));

        AccessKey accessKey = accessKeyRepository.findById(request.id())
                .orElseThrow(() -> new CustomException(StatusCode.ACCESS_KEY_NOT_FOUND, StatusCode.ACCESS_KEY_NOT_FOUND.getMessage()));

        accessKey.setStatus(Status.REVOKED);
        accessKey.setRevoked_at(LocalDateTime.now());

        return accessKeyMapper.toAccessKeyResponse(accessKeyRepository.save(accessKey));
    }

    @Override
    public Boolean validateAccessKey(String apiKey) {
        AccessKey accessKey = accessKeyRepository.findByApiKey(apiKey)
                .orElse(null);

        if(accessKey == null || accessKey.getStatus() == Status.REVOKED){
            return false;
        }

        return accessKey.getExpires_at() == null || !LocalDateTime.now().isAfter(accessKey.getExpires_at());
    }

    @Override
    public AccessKeyResponse findAccessKeyByApiKey(String apiKey) {
        AccessKey accessKey = accessKeyRepository.findByApiKey(apiKey)
                .orElseThrow(
                        () -> new CustomException(StatusCode.ACCESS_KEY_NOT_FOUND, StatusCode.ACCESS_KEY_NOT_FOUND.getMessage())
                );

        return accessKeyMapper.toAccessKeyResponse(accessKey);
    }

    @Override
    public AccessKeyResponse findAccessKeyByUserId(int userId) {
        return null;
    }

    @Override
    public AccessKeyResponse getAllUserAccessKeys(int userId) {
        return null;
    }
}
