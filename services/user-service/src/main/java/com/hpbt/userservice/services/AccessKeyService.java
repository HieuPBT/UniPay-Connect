package com.hpbt.userservice.services;

import com.hpbt.userservice.dto.requests.AccessKeyRequest;
import com.hpbt.userservice.dto.requests.RevokedAccessKeyRequest;
import com.hpbt.userservice.dto.responses.AccessKeyResponse;

public interface AccessKeyService {
    AccessKeyResponse createAccessKey();
    AccessKeyResponse revokeAccessKey(RevokedAccessKeyRequest request);
    Boolean validateAccessKey(String apiKey);
    AccessKeyResponse findAccessKeyByApiKey(String apiKey);
    AccessKeyResponse findAccessKeyByUserId(int userId);
    AccessKeyResponse getAllUserAccessKeys(int userId);
}
