package com.hpbt.notificationservice.dto.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hpbt.event.UserRegisterInfo;
import com.hpbt.notificationservice.entities.NotificationStatus;
import com.hpbt.notificationservice.entities.NotificationType;
import com.hpbt.notificationservice.entities.RefundInfo;
import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record NotificationResponse(
        String id,
        String userId,
        NotificationStatus notificationStatus,
        NotificationType notificationType,
        RefundInfo refundInfo,
        UserRegisterInfo userRegisterInfo
) {
}
