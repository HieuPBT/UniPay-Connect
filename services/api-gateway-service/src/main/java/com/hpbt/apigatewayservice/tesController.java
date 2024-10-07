package com.hpbt.apigatewayservice;

import com.hpbt.apigatewayservice.services.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class tesController {
    final UserService userService;

    @PostMapping("/test")
    public ResponseEntity<?> test(@RequestBody String apiKey){
//        userService.validateApiKey(apiKey);

        return ResponseEntity.ok(userService.validateApiKey(apiKey));
    }
}
