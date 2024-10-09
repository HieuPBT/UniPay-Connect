package com.hpbt.notificationservice.services;

import com.hpbt.notificationservice.dto.requests.NotificationRequest;
import com.hpbt.notificationservice.dto.responses.NotificationResponse;
import com.hpbt.notificationservice.entities.Notification;

public interface NotificationService {
    NotificationResponse save(NotificationRequest request);
}
