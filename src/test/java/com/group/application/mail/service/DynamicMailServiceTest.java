package com.group.application.mail.service;

import jakarta.mail.MessagingException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DynamicMailServiceTest {

    @Autowired
    DynamicMailService mailService;

    @Test
    void sendMail() throws MessagingException {

        List<String> receive = new ArrayList<>();
        receive.add("041500im@naver.com");

        String username = "king0031443@gmail.com";
        String password = "rvkj kqbk mcyh ngxc";
        String subject = "엄마에게";
        String content = "민규!";

        mailService.SendEmail(receive, subject, username, content, password);
    }

}