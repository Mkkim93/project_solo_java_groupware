package com.group.application.mail.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Properties;

@Slf4j
@Service
public class DynamicMailService {

    public void SendEmail(List<String> recipient, String subject, String username, String content, String password) throws MessagingException {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername(username);
        mailSender.setPassword(password);

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(username);
        helper.setTo(recipient.toArray(new String[0]));
        /*helper.setTo(recipient); // 수신자*/
        helper.setSubject(subject);
        helper.setText(content, true);

        mailSender.send(message);
        log.info("Email sent successfully");
    }
}
