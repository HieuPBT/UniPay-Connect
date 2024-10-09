package com.hpbt.notificationservice.controllers;

import com.hpbt.event.UserRegisterInfo;
import com.hpbt.notificationservice.dto.requests.NotificationRequest;
import com.hpbt.notificationservice.entities.NotificationStatus;
import com.hpbt.notificationservice.entities.NotificationType;
import com.hpbt.notificationservice.services.NotificationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class NotificationController {
    NotificationService notificationService;

    @KafkaListener(topics = "user-registration")
    public void userRegistration(UserRegisterInfo user) {
        log.info("message recieved: {}", user);
        notificationService.save(
                NotificationRequest.builder()
                        .userId(user.id().toString())
                        .notificationType(NotificationType.USER_REGISTER)
                        .userRegisterInfo(user)
                        .notificationStatus(NotificationStatus.PENDING)
                        .build()
        );
    }

}
