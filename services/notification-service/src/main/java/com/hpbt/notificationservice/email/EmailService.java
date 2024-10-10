package com.hpbt.notificationservice.email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EmailService {
    private static final Logger log = LoggerFactory.getLogger(EmailService.class);
    JavaMailSender javaMailSender;
    //    MailSender mailSender;
    SpringTemplateEngine springTemplateEngine;

    @Async
    public void sentWelcomeUserEmail(String destination, String username) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper =
                new MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
        mimeMessageHelper.setFrom("duesjacky@gmail.com");

        String templateName = EmailTemplate.WELCOME_USER.getTemplate();

        Map<String, Object> variables = new HashMap<>();
        variables.put("username", username);

        Context context = new Context();
        context.setVariables(variables);
        mimeMessageHelper.setSubject(EmailTemplate.WELCOME_USER.getSubject());

        try{
            String htmlTemplate = springTemplateEngine.process(templateName, context);
            mimeMessageHelper.setText(htmlTemplate, true);
            mimeMessageHelper.setTo(destination);
            javaMailSender.send(mimeMessage);
            log.info(String.format("Welcome user email sent. Destination: %s with template %s", destination, templateName));
        } catch(MessagingException ex){
            log.warn("Warning - cannot send email tp {}", destination);
        }
    }

    @Async
    public void sentMoneyRefund(String destination, String username, String orderId, String amount, String method, String refundDate) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper =
                new MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
        mimeMessageHelper.setFrom("duesjacky@gmail.com");

        String templateName = EmailTemplate.MONEY_REFUNDD.getTemplate();

        Map<String, Object> variables = new HashMap<>();
        variables.put("username", username);
        variables.put("orderId", orderId);
        variables.put("amount", amount);
        variables.put("method", method);
        variables.put("refundDate", refundDate);

        Context context = new Context();
        context.setVariables(variables);
        mimeMessageHelper.setSubject(EmailTemplate.MONEY_REFUNDD.getSubject());

        try{
            String htmlTemplate = springTemplateEngine.process(templateName, context);
            mimeMessageHelper.setText(htmlTemplate, true);
            mimeMessageHelper.setTo(destination);
            javaMailSender.send(mimeMessage);
            log.info(String.format("Money Refund email sent. Destination: %s with template %s", destination, templateName));
        } catch(MessagingException ex){
            log.warn("Warning - cannot send email tp {}", destination);
        }
    }
}
