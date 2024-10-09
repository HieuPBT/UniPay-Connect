package com.hpbt.notificationservice.mappers;

import com.hpbt.notificationservice.dto.requests.NotificationRequest;
import com.hpbt.notificationservice.dto.responses.NotificationResponse;
import com.hpbt.notificationservice.entities.Notification;
import org.springframework.stereotype.Service;

@Service
public class NotificationMapper {
    public Notification toNotification(NotificationRequest request){
        return Notification.builder()
                .userId(request.userId())
                .status(request.notificationStatus())
                .notificationType(request.notificationType())
                .refundInfo(request.refundInfo())
                .userRegisterInfo(request.userRegisterInfo())
                .build();
    }

    public NotificationResponse toNotificationResponse(Notification notification){
        return NotificationResponse.builder()
                .id(notification.getId())
                .userId(notification.getUserId())
                .notificationStatus(notification.getStatus())
                .notificationType(notification.getNotificationType())
                .refundInfo(notification.getRefundInfo())
                .userRegisterInfo(notification.getUserRegisterInfo())
                .build();
    }
}
