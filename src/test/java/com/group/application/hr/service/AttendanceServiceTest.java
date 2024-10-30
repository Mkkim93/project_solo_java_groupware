package com.group.application.hr.service;

import com.group.application.hr.dto.AttendanceDTO;
import com.group.domain.hr.entity.Attendance;
import com.group.domain.hr.repository.AttendanceRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AttendanceServiceTest {

    @Autowired
    private AttendanceService attendanceService;

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Test
    @Rollback(value = true)
    void attOnTest() {
        AttendanceDTO attendanceDTO = new AttendanceDTO();
        attendanceDTO.setEmployee(1);

        AttendanceDTO attendanceDTOResult = attendanceService.attOn(attendanceDTO);

        System.out.println("attendanceDTOResult.getEmpName() = " + attendanceDTOResult.getEmpName()); // null..
        System.out.println("attendanceDTOResult.getAttOn() = " + attendanceDTOResult.getAttOn());
        System.out.println("attendanceDTOResult.getEmployee() = " + attendanceDTOResult.getEmployee());
    }

    @Test
    void attOffTest() {
        Integer id = 1;
        boolean existsById = attendanceRepository.existsById(id);
        System.out.println("existsById = " + existsById);
    }

    @Test
    void findById() {
        AttendanceDTO attendanceDTO = new AttendanceDTO();
        attendanceDTO.setId(1);
        Attendance attendance = attendanceRepository.findById(attendanceDTO.getId()).get();
        // attendanceService.attOn(attendanceDTO);
        System.out.println("attendance.getId() = " + attendance.getId());
        System.out.println("attendance.getEmployee() = " + attendance.getEmployee());
        System.out.println("attendance.getAttOn() = " + attendance.getAttOn());
    }
}