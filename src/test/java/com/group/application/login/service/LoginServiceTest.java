package com.group.application.login.service;

import com.group.application.hr.dto.EmployeeDTO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LoginServiceTest {

    @Autowired
    LoginService loginService;

    @Autowired
    BCryptPasswordEncoder encoder;

    @Test
    @DisplayName("1. 정상 로그인")
    void loginPass() {
        EmployeeDTO dto = new EmployeeDTO();
        dto.setEmpEmail("test@gmail.com");
        dto.setEmpPass("1234");
        EmployeeDTO e = loginService.findByEmpInfo(dto);
        assertThat(e.getEmpEmail()).isEqualTo(dto.getEmpEmail());
        boolean matches = encoder.matches(dto.getEmpPass(), e.getEmpPass());
        assertThat(matches).isTrue();
    }

    @Test
    @DisplayName("2. 로그인 실패(1) (empEmail 검증 실패)")
    void loginFail_EmpEmail() {
        EmployeeDTO dto = new EmployeeDTO();
        dto.setEmpEmail("fail@gmail.com");
        dto.setEmpPass("1234");
        loginService.findByEmpInfo(dto);
    }

    @Test
    @DisplayName("3. 로그인 실패(2) (empPass 입력 오류)")
    void loginFail_EmpPass() {
        EmployeeDTO dto = new EmployeeDTO();
        dto.setEmpEmail("test@gmail.com");
        dto.setEmpPass("4321");
        loginService.findByEmpInfo(dto);
    }

}