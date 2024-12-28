/*
package com.group.application.admin.service;

import com.group.application.admin.dto.RegisterDTO;
import com.group.exception.DuplicateEntityException;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@Slf4j
@SpringBootTest
class AdminServiceTest {

    @Autowired AdminService adminService;

    @Test
    @DisplayName("사원 등록 시 이메일 중복 체크")
    void duplicationId() {
        // given
        RegisterDTO registerDTO = new RegisterDTO();
        registerDTO.setEmpMail("mkkim92@gmail.com");

        // then
        String duplicated = adminService
                .duplicateEmail(registerDTO.getEmpMail());

        // when
    }

    @Test
    @DisplayName("사원 등록")
    void register() {
        // given
        RegisterDTO registerDTO = new RegisterDTO();
        registerDTO.setEmpMail("sample2@exam.com");
        registerDTO.setEmpPass("1234");
        registerDTO.setDepartId(2);

        // then
        adminService.registration(registerDTO);
    }
}*/
