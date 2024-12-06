package com.group.web.hr.controller.api;

import com.group.application.hr.dto.AttendanceDTO;
import com.group.application.hr.dto.EmployeeDTO;
import com.group.application.hr.service.AttendanceService;
import com.group.application.hr.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AttendanceApiControllerTest {
    @Autowired
    EmployeeService employeeService;
    @Autowired
    AttendanceService attendanceService;

    @Test
    void workInTest() {
        EmployeeDTO empDto = new EmployeeDTO();
        AttendanceDTO attDto = new AttendanceDTO();

        empDto.setEmpUUID("6103a7be-69ac-47f4-89ee-267997f87a64");
        EmployeeDTO findDto = employeeService.findByAll(empDto);

        attDto.setEmployee(findDto.getId());

        AttendanceDTO resultAttDto = attendanceService.workIn(attDto);
        System.out.println("resultAttDto.getAttOn() = " + resultAttDto.getAttOn());
        System.out.println("resultAttDto.getEmployee() = " + resultAttDto.getEmployee());
    }
}