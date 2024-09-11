package com.hpbt.userservice.services;

import com.hpbt.userservice.dto.requests.UserRequest;
import com.hpbt.userservice.dto.responses.UserResponse;
import com.hpbt.userservice.mappers.UserMapper;
import com.hpbt.userservice.repositories.UserRepository;
import com.hpbt.userservice.utils.exceptions.UserExistException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


public interface UserService{
    UserResponse createUser(UserRequest userRequest) throws UserExistException;
}
