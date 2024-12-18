package com.group.domain.hr.repository;

import com.group.application.hr.dto.DepartmentDTO;
import com.group.application.hr.dto.EmployeeDTO;
import com.group.domain.hr.entity.Employee;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EmployeeRepositoryImplTest {

    @Autowired EmployeeRepositoryImpl employeeRepositoryImpl;
    @Autowired EmployeeRepository employeeRepository;

    @Test
    void employeeDepartQuerydslTest() {
        Integer id = 1;
        DepartmentDTO empInfo = employeeRepositoryImpl.findByEmpDepartInfo(id);
        System.out.println("empInfo.getDeptName() = " + empInfo.getDeptName());
        System.out.println("empInfo.getEmpName() = " + empInfo.getEmpName());
    }

    @Test
    @DisplayName("e-mail 로 사원 객체 조회")
    void searchByEmployeeEntity() {
        Employee employee = employeeRepository.findByEmpEmail("alsrb362@daum.net");
        System.out.println("employee.getEmpEmail() = " + employee.getEmpEmail());
        System.out.println("employee.getEmpPass() = " + employee.getEmpPass());
    }
}