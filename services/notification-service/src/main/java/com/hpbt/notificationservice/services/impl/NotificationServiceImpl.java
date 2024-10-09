package com.hpbt.notificationservice.services.impl;

import com.hpbt.notificationservice.dto.requests.NotificationRequest;
import com.hpbt.notificationservice.dto.responses.NotificationResponse;
import com.hpbt.notificationservice.entities.Notification;
import com.hpbt.notificationservice.mappers.NotificationMapper;
import com.hpbt.notificationservice.repositories.NotificationRepository;
import com.hpbt.notificationservice.services.NotificationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class NotificationServiceImpl implements NotificationService {
    NotificationRepository notificationRepository;
    NotificationMapper notificationMapper;

    @Override
    public NotificationResponse save(NotificationRequest request) {
        Notification savedNotification = notificationRepository.save(notificationMapper.toNotification(request));
        return notificationMapper.toNotificationResponse(savedNotification);
    }
}
