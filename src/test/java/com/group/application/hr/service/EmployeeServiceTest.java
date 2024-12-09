package com.group.application.hr.service;

import com.fasterxml.jackson.databind.deser.std.StdKeyDeserializer;
import com.group.application.hr.dto.AttendanceDTO;
import com.group.application.hr.dto.DepartmentDTO;
import com.group.application.hr.dto.EmployeeDTO;
import com.group.application.login.service.JoinService;
import com.group.domain.hr.entity.Attendance;
import com.group.domain.hr.entity.Employee;
import com.group.domain.hr.entity.QEmployee;
import com.group.domain.hr.repository.EmployeeRepository;
import com.querydsl.core.QueryFactory;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;


@SpringBootTest
@RequiredArgsConstructor
class EmployeeServiceTest {

    @Autowired JoinService joinService;
    @Autowired EmployeeService employeeService;
    @Autowired EmployeeRepository employeeRepository;
    @Autowired AttendanceService attendanceService;
    @Autowired EntityManager em;

    JPAQueryFactory queryFactory;

    @BeforeEach
    public void beforeTestQuerydsl() {
        queryFactory = new JPAQueryFactory(em);
    }

    @Test
    @DisplayName("아이디 중복 체크")
    public void existByEmail() {
        Boolean existEmpEmail = employeeRepository.existsByEmpEmail("king00313@naver.com"); // 결과가 false 면 회원가입 진행 true 면 중복 회원
        System.out.println("existEmpEmail = " + existEmpEmail);
        System.out.println("!existEmpEmail = " + !existEmpEmail);
        assertThat(existEmpEmail).isFalse();
    }

    @Test
    @DisplayName("ID 로 회원 정보 조회")
    public void findByIdInformation() {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(1);
        EmployeeDTO byId = employeeService.findById(employeeDTO.getId());
        System.out.println("byId.getEmpName() = " + byId.getEmpName());
        assertThat(byId).extracting("id").isEqualTo(1);
    }

    @Test
    @DisplayName("회원 정보 조회")
    void findByAll() {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setEmpEmail("king00314@naver.com");
        employeeDTO.setEmpPass("1234");

        employeeService.findByAll(employeeDTO);
    }


    @Test
    @DisplayName("uuid 로 회원 정보 조회하기")
    void findByUUID() {
        EmployeeDTO dto = new EmployeeDTO();
        dto.setEmpUUID("f6c3bd62-9dd2-46a3-95ec-bcf760428acd");
        EmployeeDTO result = employeeService.findByAll(dto);
        System.out.println("dto.getId() = " + result.getId());
        System.out.println("dto.getEmpEmail() = " + result.getEmpEmail());
        System.out.println("dto.getEmpName() = " + result.getEmpName());
        System.out.println("dto.getEmpUUID() = " + result.getEmpUUID());
    }
}