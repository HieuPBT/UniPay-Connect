package com.hpbt.userservice.services;

import com.hpbt.userservice.dto.requests.AccessKeyRequest;
import com.hpbt.userservice.dto.requests.UserRequest;
import com.hpbt.userservice.dto.requests.ValidateApiKeyRequest;
import com.hpbt.userservice.dto.responses.AccessKeyResponse;
import com.hpbt.userservice.dto.responses.UserResponse;
import com.hpbt.userservice.exceptions.UserExistException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Set;


public interface UserService{
    UserResponse createUser(UserRequest userRequest) throws UserExistException;
    UserResponse getUserById(int id);
    UserResponse getUserByUsername(String username);
    Page<UserResponse> getAllUsers(Pageable pageable);
    Boolean isUserExist(int id);
    UserResponse findUserByApiKey (ValidateApiKeyRequest request);
    Page<AccessKeyResponse> findAllAccessKeysByUserId(int userId, Pageable pageable);
}
