package com.hpbt.notificationservice.dto.requests;

import com.hpbt.notificationservice.entities.NotificationStatus;

public record UpdateNotificationRequest(
        String id,
        NotificationStatus notificationStatus
) {
}
