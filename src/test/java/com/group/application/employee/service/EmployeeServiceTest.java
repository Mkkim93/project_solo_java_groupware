package com.group.application.employee.service;

import com.group.application.hr.dto.AttendanceDTO;
import com.group.application.hr.dto.DepartmentDTO;
import com.group.application.hr.dto.EmployeeDTO;
import com.group.application.hr.service.EmployeeService;
import com.group.domain.hr.entity.Employee;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static com.group.domain.hr.enums.EmpIsAdmin.USER;
import static com.group.domain.hr.enums.EmpJoinYN.JOIN;

@SpringBootTest
@RequiredArgsConstructor
class EmployeeServiceTest {

    @Autowired
    EmployeeService employeeService;

    @Test
    @DisplayName("회원 가입")
    public void saveEmployee() {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        DepartmentDTO deptIdDto = new DepartmentDTO(5);

        employeeDTO.setEmpPass("4223");
        employeeDTO.setEmpName("손종우");
        employeeDTO.setEmpRank("사원");
        employeeDTO.setEmpRegNo("4449-1234");
        employeeDTO.setEmpNickName("스컬지");
        employeeDTO.setUserTel("3333-2333");
        employeeDTO.setUserEmail("ss@mail.com");
        employeeDTO.setEmpEmail("ss@mail.com");
        employeeDTO.setEmpMileage(400);
        employeeDTO.setEmpJoinYn(JOIN);
        employeeDTO.setEmpIsAdmin(USER);
        employeeDTO.setEmpNo("EMP13");
        employeeDTO.setDeptId(deptIdDto.getId());

        employeeService.saveEmployee(employeeDTO);
    }

    @Test
    @DisplayName("모든 사원 조회")
    public void findAllEmployeeList() {
        List<Employee> allEmployee = employeeService.findAllEmployee();
        allEmployee.stream().forEach(System.out::println);
    }

    @Test
    @DisplayName("사원 ID로 정보 조회")
    public void findByIdEmployee() {
        Integer empIds = 9;
        List<Employee> employees = employeeService.findByEmployee(empIds);
        employees.stream().forEach(System.out::println);
    }

    @Test
    @DisplayName("나의 근태 조회")
    public void myAttInfo() {
        String empName = "김민규";
        List<Object[]> byMyAttInfo = employeeService.findByMyAttInfo(empName);
    }
}