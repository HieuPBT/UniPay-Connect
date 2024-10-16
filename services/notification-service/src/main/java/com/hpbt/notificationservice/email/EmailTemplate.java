package com.hpbt.notificationservice.email;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum EmailTemplate {
    WELCOME_USER("user-registration-welcome.html", "user registration successfully processed"),
    MONEY_REFUNDD("money-refund.html", "money refund successfully processed");

    @Getter
    String template;
    @Getter
    String subject;

//    EmailTemplate(String template, String subject) {
//        this.template = template;
//        this.subject = subject;
//    }
}
