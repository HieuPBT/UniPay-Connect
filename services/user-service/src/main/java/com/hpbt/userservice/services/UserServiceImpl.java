package com.hpbt.userservice.services;

import com.hpbt.userservice.dto.requests.UserRequest;
import com.hpbt.userservice.dto.responses.UserResponse;
import com.hpbt.userservice.mappers.UserMapper;
import com.hpbt.userservice.repositories.UserRepository;
import com.hpbt.userservice.utils.exceptions.UserExistException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;


    @Override
    public UserResponse createUser(UserRequest userRequest) throws UserExistException {
        if(userRepository.existsByUsername(userRequest.username())){
            throw new UserExistException("User with username " + userRequest.username() + " already exists");
        }
        if(userRepository.existsByEmail(userRequest.email())){
            throw new UserExistException("User with email " + userRequest.email() + " already exists");
        }
        var user = userRepository.save(userMapper.toUser(userRequest));

        return userMapper.toUserResponse(user);
    }
}
