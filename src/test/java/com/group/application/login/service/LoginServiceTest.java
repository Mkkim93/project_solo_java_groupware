package com.group.application.login.service;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LoginServiceTest {

    @Autowired
    LoginService loginService;

    @Test
    void findEmpInfo() {
        String empEmail = "king00314@naver.com";
        String empPass = "1234";
        loginService.findByEmpInfo(empEmail, empPass);
    }

}