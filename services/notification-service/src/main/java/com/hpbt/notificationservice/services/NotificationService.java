package com.hpbt.notificationservice.services;

import com.hpbt.notificationservice.dto.requests.NotificationRequest;
import com.hpbt.notificationservice.dto.requests.UpdateNotificationRequest;
import com.hpbt.notificationservice.dto.responses.NotificationResponse;
import com.hpbt.notificationservice.entities.Notification;
import com.hpbt.notificationservice.repositories.NotificationRepository;

public interface NotificationService {
    NotificationResponse save(NotificationRequest request);
    NotificationResponse update(UpdateNotificationRequest request);
}
