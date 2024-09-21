package com.hpbt.userservice.services;

import com.hpbt.userservice.dto.requests.AccessKeyRequest;
import com.hpbt.userservice.dto.responses.AccessKeyResponse;
import com.hpbt.userservice.entities.User;
import com.hpbt.userservice.exceptions.CustomException;
import com.hpbt.userservice.exceptions.StatusCode;
import com.hpbt.userservice.mappers.AccessKeyMapper;
import com.hpbt.userservice.repositories.AccessKeyRepository;
import com.hpbt.userservice.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccessKeyService {
    private final AccessKeyRepository accessKeyRepository;
    private final UserRepository userRepository;
    private final AccessKeyMapper accessKeyMapper;

    public AccessKeyResponse createAccessKey(AccessKeyRequest accessKeyRequest) {
        User user = userRepository.findById(accessKeyRequest.userId())
                .orElseThrow(() -> new CustomException(StatusCode.USER_NOT_FOUND, "User with id " + accessKeyRequest.userId() + " does not exist"));

        var accessKey = accessKeyRepository.save(accessKeyMapper.toAccessKey(accessKeyRequest, user));

        return accessKeyMapper.toAccessKeyResponse(accessKey);
    }

}
