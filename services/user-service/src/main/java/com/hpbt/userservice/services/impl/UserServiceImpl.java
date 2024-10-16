package com.hpbt.userservice.services.impl;

import com.hpbt.event.UserRegisterInfo;
import com.hpbt.userservice.dto.requests.UserRequest;
import com.hpbt.userservice.dto.requests.ValidateApiKeyRequest;
import com.hpbt.userservice.dto.responses.AccessKeyResponse;
import com.hpbt.userservice.dto.responses.UserResponse;
import com.hpbt.userservice.entities.AccessKey;
import com.hpbt.userservice.entities.Status;
import com.hpbt.userservice.entities.User;
import com.hpbt.userservice.exceptions.CustomException;
import com.hpbt.userservice.exceptions.StatusCode;
import com.hpbt.userservice.mappers.AccessKeyMapper;
import com.hpbt.userservice.mappers.UserMapper;
import com.hpbt.userservice.repositories.AccessKeyRepository;
import com.hpbt.userservice.repositories.UserRepository;
import com.hpbt.userservice.services.AccessKeyService;
import com.hpbt.userservice.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final AccessKeyMapper accessKeyMapper;
    private final AccessKeyService accessKeyService;
    private final AccessKeyRepository accessKeyRepository;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Override
    public Boolean isUserExist(int id) {
        return userRepository.existsById(id);
    }

    @Override
    public UserResponse findUserByApiKey(ValidateApiKeyRequest request) {
        System.out.println(request.apiKey());
        AccessKey accessKey = accessKeyRepository.findByApiKey(request.apiKey())
                .orElseThrow(
                        () -> new CustomException(StatusCode.ACCESS_KEY_NOT_FOUND, StatusCode.ACCESS_KEY_NOT_FOUND.getMessage())
                );
//        if (accessKey.getStatus() == Status.REVOKED || accessKey.getExpires_at().isBefore(LocalDateTime.now())) {
//            throw new CustomException(StatusCode.BAD_REQUEST, apiKey);
//        }
        return userMapper.toUserResponse(accessKey.getUser());
    }

    @Override
    public Page<AccessKeyResponse> findAllAccessKeysByUserId(int userId, Pageable pageable) {
        Page<AccessKey> accessKeys = accessKeyRepository.findAllByUserId(userId, pageable);
        List<AccessKeyResponse> accessKeyResponses = accessKeys.getContent()
                .stream()
                .map(accessKeyMapper::toAccessKeyResponse)
                .collect(Collectors.toList());
        return new PageImpl<>(accessKeyResponses, pageable, accessKeys.getTotalElements());
    }

    @Override
    public UserResponse createUser(UserRequest userRequest) {
        if (userRepository.existsByUsername(userRequest.username())) {
            throw new CustomException(StatusCode.USER_EXISTED, "User with username " + userRequest.username() + " already exists");
        }
        if (userRepository.existsByEmail(userRequest.email())) {
            throw new CustomException(StatusCode.USER_EXISTED, "User with email " + userRequest.email() + " already exists");
        }
        var user = userRepository.save(userMapper.toUser(userRequest));

        UserRegisterInfo userRegisterInfo = UserRegisterInfo.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .fullName(user.getFullName())
                .avatar(user.getAvatar())
                .build();

        kafkaTemplate.send("user-registration", userRegisterInfo);

        return userMapper.toUserResponse(user);
    }

    @Override
    public UserResponse getUserById(int id) {
        if (!userRepository.existsById(id)) {
            throw new CustomException(StatusCode.USER_NOT_FOUND, "User with id " + id + " does not exist");
        }

        return userMapper.toUserResponse(userRepository.findUserById(id));
    }

    @Override
    public UserResponse getUserByUsername(String username) {
        return userMapper.toUserResponse(userRepository.findByUsername(username)
                .orElseThrow(() -> new CustomException(StatusCode.USER_NOT_FOUND, "User with name " + username + " does not exist")));
    }

    @Override
    public Page<UserResponse> getAllUsers(Pageable pageable) {
        Page<User> users = userRepository.findAllBy(pageable);

        List<UserResponse> userResponses = users.getContent()
                .stream()
                .map(userMapper::toUserResponse)
                .collect(Collectors.toList());

        return new PageImpl<>(userResponses, pageable, users.getTotalElements());
    }


//    public AuthenticationResponse authenticateUser(AuthenticationRequest authenticationRequest) {
//        User user = userRepository.findByUsername(authenticationRequest.username())
//                .orEl
//    }
}
