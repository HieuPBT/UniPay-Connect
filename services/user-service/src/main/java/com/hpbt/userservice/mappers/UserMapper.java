package com.hpbt.userservice.mappers;

import com.hpbt.userservice.dto.requests.UserRequest;
import com.hpbt.userservice.dto.responses.UserResponse;
import com.hpbt.userservice.entities.User;
import com.hpbt.userservice.entities.UserRole;
import com.hpbt.userservice.services.CloudinaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserMapper {
    private final PasswordEncoder passwordEncoder;
    private final CloudinaryService cloudinaryService;

    public User toUser(UserRequest userRequest){
        if(userRequest == null){
            return null;
        }

        String avatarUrl = null;
        MultipartFile avatar = userRequest.avatar();
        if(avatar != null && !avatar.isEmpty()){
            avatarUrl = cloudinaryService.uploadFile(avatar);
        }

        return User.builder()

                .username(userRequest.username())
                .password(passwordEncoder.encode(userRequest.password()))
                .email(userRequest.email())
                .fullName(userRequest.fullName())
                .phoneNumber(userRequest.phoneNumber())
                .userRole(UserRole.MERCHANT)
                .avatar(avatarUrl)
                .createdAt(LocalDateTime.now())
                .active(true)
                .build();
    }

    public UserResponse toUserResponse(User user){
        return new UserResponse(
                user.getId(),
                user.getUsername(),
                user.getFullName(),
                user.getEmail(),
                user.getPhoneNumber(),
                user.getAvatar()
        );
    }
}
