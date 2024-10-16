package com.hpbt.notificationservice.entities;

//public enum NotificationType {
//    ORDER_CONFIRMATION,
//    PAYMENT_CONFIRMATION,
//}

import lombok.*;
import lombok.experimental.FieldNameConstants;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
//@FieldNameConstants(level = AccessLevel.PRIVATE)
//@Document
public enum NotificationType {
//    @Id
//    String id;
//
//    String name;
//
//    String description;
//
//    @CreatedDate
//    LocalDateTime createdAt;
    USER_REGISTER,
    MONEY_REFUND
}
