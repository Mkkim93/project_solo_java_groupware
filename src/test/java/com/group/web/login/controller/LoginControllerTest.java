package com.group.web.login.controller;

import com.group.application.hr.dto.EmployeeDTO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.context.SecurityContextHolder;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LoginControllerTest {

    @Test
    void token() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println(name);
    }
}