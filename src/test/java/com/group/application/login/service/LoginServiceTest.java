package com.group.application.login.service;

import com.group.application.hr.dto.EmployeeDTO;
import com.group.domain.hr.entity.Employee;
import com.group.domain.hr.repository.EmployeeRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class LoginServiceTest {

    @Autowired
    LoginService loginService;

    @Autowired
    BCryptPasswordEncoder encoder;

    @Autowired
    EmployeeRepository employeeRepository;

    @Test
    @DisplayName("1. 정상 로그인")
    void loginPass() {
        EmployeeDTO dto = new EmployeeDTO();
        dto.setEmpEmail("test@gmail.com");
        dto.setEmpPass("1234");
        EmployeeDTO e = loginService.login(dto);
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
        loginService.login(dto);
    }

    @Test
    @DisplayName("3. 로그인 실패(2) (empPass 입력 오류)")
    void loginFail_EmpPass() {
        EmployeeDTO dto = new EmployeeDTO();
        dto.setEmpEmail("test@gmail.com");
        dto.setEmpPass("4321");
        loginService.login(dto);
    }

    @Test
    @DisplayName("findByUUID")
    void findUUID() {
        String empEmail = "222@naver.com";
        String uuid = employeeRepository.findByEmpUUID(empEmail);
        System.out.println("uuid.getEmpUUID() = " + uuid);

    }

    @Test
    @DisplayName("uuid 컬럼을 조건으로 회원의 정보 조회하기 (dtO)")
    void loginAuthDto() {
        String empUUID = "f7c9a645-c846-4219-a191-b742ef2161b1";
        EmployeeDTO dto = employeeRepository.findByEmployee(empUUID);
        System.out.println("dto.getEmpEmail() = " + dto.getEmpEmail());
        System.out.println("dto.getEmpName() = " + dto.getEmpName());
        System.out.println("dto.getEmpUUID() = " + dto.getEmpUUID());
    }

    @Test
    @DisplayName("uuid 컬럼을 조건으로 회원의 정보 조회(entity)")
    void loginAuthEntity() {
        String empUUID = "f7c9a645-c846-4219-a191-b742ef2161b1";
        Employee entity = employeeRepository.findByEmployeeEntity(empUUID);
        System.out.println("entity.getEmpEmail() = " + entity.getEmpEmail());
        System.out.println("entity.getEmpName() = " + entity.getEmpName());
        System.out.println("entity.getEmpUUID() = " + entity.getEmpUUID());
    }

}