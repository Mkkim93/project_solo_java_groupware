package com.group.application.login.service;

import com.group.application.hr.dto.EmployeeDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
@RequiredArgsConstructor
class JoinServiceTest {

    @Autowired
    JoinService joinService;

    @Test
    @DisplayName("회원가입 테스트")
    public void joinTest() {
        EmployeeDTO empDto = new EmployeeDTO();
        empDto.setEmpEmail("test123@gmail.com");
        empDto.setEmpPass("1234");
        joinService.save(empDto);
    }
}