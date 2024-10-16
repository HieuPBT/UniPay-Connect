package com.hpbt.userservice.services;

import com.hpbt.userservice.dto.requests.AccessKeyRequest;
import com.hpbt.userservice.dto.requests.UserRequest;
import com.hpbt.userservice.dto.responses.AccessKeyResponse;
import com.hpbt.userservice.dto.responses.UserResponse;
import com.hpbt.userservice.exceptions.UserExistException;


public interface UserService{
    UserResponse createUser(UserRequest userRequest) throws UserExistException;
    UserResponse getUserById(int id);
}
