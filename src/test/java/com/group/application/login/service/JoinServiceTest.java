package com.group.application.login.service;

import com.group.application.hr.dto.EmployeeDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
@RequiredArgsConstructor
class JoinServiceTest {

    @Autowired
    JoinService joinService;

    @Test
    @DisplayName("회원가입 테스트")
    public void joinTest() throws Exception {
        EmployeeDTO empDto = new EmployeeDTO();
        empDto.setEmpEmail("alsrb362@daum.net");
        empDto.setEmpPass("1234");
        empDto.setEmpRegNo("930415-1406212");
        empDto.setUserEmail("alsrb333@daum.net");
        empDto.setEmpName("임요환");
        empDto.setEmpNickName("황제테란");
        joinService.joinProcess(empDto);
    }
}