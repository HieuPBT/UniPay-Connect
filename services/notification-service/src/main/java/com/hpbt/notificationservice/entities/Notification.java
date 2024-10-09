package com.hpbt.notificationservice.entities;

import com.hpbt.event.UserRegisterInfo;
import lombok.*;
import lombok.experimental.FieldNameConstants;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@FieldNameConstants(level = AccessLevel.PRIVATE)
public class Notification {
    @Id
    String id;

    String userId;

    NotificationStatus status;

    NotificationType notificationType;

    RefundInfo refundInfo;

    UserRegisterInfo userRegisterInfo;

    @CreatedDate
    LocalDateTime createdAt;
}
