package com.group.application.hr.service;

import com.group.application.hr.dto.AttendanceDTO;
import com.group.application.hr.dto.DepartmentDTO;
import com.group.application.hr.dto.EmployeeDTO;
import com.group.domain.hr.entity.Attendance;
import com.group.domain.hr.entity.Employee;
import com.group.domain.hr.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static com.group.domain.hr.enums.EmpIsAdmin.USER;
import static com.group.domain.hr.enums.EmpJoinYN.JOIN;

@SpringBootTest
@RequiredArgsConstructor
class EmployeeServiceTest {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    AttendanceService attendanceService;

    @Test
    @DisplayName("회원 가입")
    public void saveEmployee() {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        DepartmentDTO deptIdDto = new DepartmentDTO(1);
        AttendanceDTO attendanceDTO = new AttendanceDTO();
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
        employeeDTO.setEmpIsAdmin("USER");
        employeeDTO.setEmpNo("EMP02");
        employeeDTO.setDeptId(deptIdDto.getId());
        Attendance attendance = attendanceService.saveAttendance(attendanceDTO);
        Integer id = attendance.getId();
        employeeDTO.setAttId(id);

    }

    @Test
    @DisplayName("모든 사원 조회")
    public void findAllEmployeeList() {
        List<Employee> allEmployee = employeeService.findAllEmployee();
        allEmployee.forEach(System.out::println);
    }

    @Test
    @DisplayName("사원 ID로 정보 조회")
    public void findByIdEmployee() {
        Integer empIds = 1;
        List<Employee> employees = employeeService.findByEmployee(empIds);
        employees.stream().forEach(System.out::println);
    }

    @Test
    @DisplayName("나의 근태 조회")
    public void myAttInfo() {
        String empName = "손종우";
        List<Object[]> byMyAttInfo = employeeService.findByMyAttInfo(empName);
        printList(byMyAttInfo);
    }

    private static void printList(List<Object[]> byMyAttInfo) {
        for (Object[] array : byMyAttInfo) {
            Arrays.stream(array).toList().forEach(System.out::println);
        }
    }

    @Test
    @DisplayName("나의 정보 수정")
    public void updateMyInfo() {
        Integer empId = 1;
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setEmpName("손종우");
        employeeDTO.setEmpPass("4444");
        employeeDTO.setUserEmail("dddd@naver.com");
        employeeDTO.setEmpNickName("천재테란");
        employeeService.updateMyInfo(empId, employeeDTO);
    }

    @Test
    @DisplayName("임시테스트_사원이름으로 정보 조회하기")
    public void findByUserName() {
        List<Employee> empNameList = employeeRepository.findByEmpName("손종우");
        empNameList.forEach(System.out::println);
    }

    @Test
    public void findAll() {
        List<Employee> employees = employeeRepository.findAll();
        for (Employee employee : employees) {
            System.out.println("employee = " + employee);
        }
    }

    @Test
    public void findByEmpEmail() {
        Employee byEmpEmail = employeeRepository.findByEmpEmail("mail");
        System.out.println(byEmpEmail.getEmpEmail());
        Assertions.assertThat(byEmpEmail.getEmpEmail()).isEqualTo("mail");
    }
}