package com.hpbt.notificationservice.controllers;

import com.hpbt.event.MoneyRefund;
import com.hpbt.event.UserRegisterInfo;
import com.hpbt.notificationservice.dto.requests.NotificationRequest;
import com.hpbt.notificationservice.email.EmailService;
import com.hpbt.notificationservice.entities.NotificationStatus;
import com.hpbt.notificationservice.entities.NotificationType;
import com.hpbt.notificationservice.services.NotificationService;
import jakarta.mail.MessagingException;
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
    EmailService emailService;

    @KafkaListener(topics = "user-registration")
    public void userRegistration(UserRegisterInfo user) throws MessagingException {
        log.info("message recieved: {}", user);
        notificationService.save(
                NotificationRequest.builder()
                        .userId(user.id().toString())
                        .notificationType(NotificationType.USER_REGISTER)
                        .userRegisterInfo(user)
                        .notificationStatus(NotificationStatus.PENDING)
                        .build()
        );
        emailService.sentWelcomeUserEmail(user.email(), user.username());
    }

    @KafkaListener(topics = "money-refund")
    public void moneyRefund(MoneyRefund user) throws MessagingException {
        log.info("message recieved: {}", user);
        notificationService.save(
                NotificationRequest.builder()
                        .userId(user.id().toString())
                        .notificationType(NotificationType.USER_REGISTER)
                        .notificationStatus(NotificationStatus.PENDING)
                        .refundInfo(user)
                        .build()
        );
        emailService.sentMoneyRefund(user.email(), user.username(), user.orderId(), user.amount().toString(), "MoMo", user.refundDate().toString());
    }
}
