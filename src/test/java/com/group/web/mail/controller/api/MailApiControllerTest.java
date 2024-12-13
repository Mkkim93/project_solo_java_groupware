package com.group.web.mail.controller.api;

import com.group.application.mail.service.DynamicMailService;
import jakarta.mail.MessagingException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MailApiControllerTest {

    @Autowired
    DynamicMailService dynamicMailService;

    @Test
    void mailSend() throws MessagingException {

            String username = "king0031443@gmail.com";
            String password = "bkvb syqd tyuu javq";
            List<String> receipt = new ArrayList<>();
            receipt.add("king00314@naver.com");
            receipt.add("alsrb362@daum.net");
            String subject = "메일 제목2";
            String content = "메일 내용2";
            receipt.stream().forEach(System.out::println);

            dynamicMailService.SendEmail(receipt, subject, username, content, password);

    }

}