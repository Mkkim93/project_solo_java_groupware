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

    @Autowired
    JoinService joinService;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    AttendanceService attendanceService;

    @Autowired
    EntityManager em;
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
    @DisplayName("회원 가입")
    public void saveEmployee() {
        EmployeeDTO empDto = new EmployeeDTO("ggg11@goolge.com", "1234", "구마적"
        ,"0392-494", "kkk@naver.com", "돼지");
        EmployeeDTO joinedDto = joinService.joinProcess(empDto);
        assertThat(joinedDto.getEmpEmail()).isEqualTo(empDto.getEmpEmail());
    }

    @Test
    @DisplayName("ID 로 회원 정보 조회")
    public void findByIdInformation() {
        Integer id = 1;
        Optional<Employee> byId = employeeRepository.findById(id);
        List<Employee> findByIdEmployee = byId.stream().toList();
        System.out.println("list = " + findByIdEmployee);
        assertThat(id).extracting("id").isEqualTo(1);
    }
}