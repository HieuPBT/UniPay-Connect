package com.hpbt.userservice.controllers;

import com.hpbt.userservice.dto.requests.UserRequest;
import com.hpbt.userservice.dto.responses.UserResponse;
import com.hpbt.userservice.services.UserService;
import com.hpbt.userservice.utils.exceptions.UserExistException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;

    @RequestMapping("/hello")
    public String hello(){
        return "Hello World";
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@ModelAttribute @Valid UserRequest request){
        try {
            UserResponse user = userService.createUser(request);

            return ResponseEntity.ok(user);
        } catch (UserExistException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}

