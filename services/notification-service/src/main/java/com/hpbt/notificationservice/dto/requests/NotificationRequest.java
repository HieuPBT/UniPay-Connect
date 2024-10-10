package com.hpbt.notificationservice.dto.requests;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hpbt.event.MoneyRefund;
import com.hpbt.event.UserRegisterInfo;
import com.hpbt.notificationservice.entities.NotificationStatus;
import com.hpbt.notificationservice.entities.NotificationType;
import lombok.Builder;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record NotificationRequest(
        String userId,
        NotificationStatus notificationStatus,
        NotificationType notificationType,
        UserRegisterInfo userRegisterInfo,
        MoneyRefund refundInfo
) {
}
