package com.hpbt.userservice.services;

import com.hpbt.userservice.dto.requests.AccessKeyRequest;
import com.hpbt.userservice.dto.requests.UserRequest;
import com.hpbt.userservice.dto.responses.AccessKeyResponse;
import com.hpbt.userservice.dto.responses.UserResponse;
import com.hpbt.userservice.entities.User;
import com.hpbt.userservice.exceptions.CustomException;
import com.hpbt.userservice.exceptions.StatusCode;
import com.hpbt.userservice.mappers.AccessKeyMapper;
import com.hpbt.userservice.mappers.UserMapper;
import com.hpbt.userservice.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final AccessKeyMapper accessKeyMapper;


    @Override
    public UserResponse createUser(UserRequest userRequest) {
        if(userRepository.existsByUsername(userRequest.username())){
            throw new CustomException(StatusCode.USER_EXISTED, "User with username " + userRequest.username() + " already exists");
        }
        if(userRepository.existsByEmail(userRequest.email())){
            throw new CustomException(StatusCode.USER_EXISTED, "User with email " + userRequest.email() + " already exists");
        }
        var user = userRepository.save(userMapper.toUser(userRequest));

        return userMapper.toUserResponse(user);
    }

    @Override
    public UserResponse getUserById(int id) {
        if(!userRepository.existsById(id)){
            throw new CustomException(StatusCode.USER_NOT_FOUND, "User with id " + id + " does not exist");
        }

        return userMapper.toUserResponse(userRepository.findUserById(id));
    }

}
