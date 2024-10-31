package com.group.application.hr.service;

import com.group.application.hr.dto.AttendanceDTO;
import com.group.domain.hr.entity.Attendance;
import com.group.domain.hr.repository.AttendanceRepository;
import com.group.domain.hr.repository.EmployRepositoryImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Date;

import static com.fasterxml.jackson.databind.type.LogicalType.DateTime;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AttendanceServiceTest {

    @Autowired
    private AttendanceService attendanceService;

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private EmployRepositoryImpl employRepository;

    @Test
    void attOnTest() {
        AttendanceDTO attendanceDTO = new AttendanceDTO();
        attendanceDTO.setEmployee(1);

        AttendanceDTO attendanceDTOResult = attendanceService.attOn(attendanceDTO);

        System.out.println("attendanceDTOResult.getAttOn() = " + attendanceDTOResult.getAttOn());
        System.out.println("attendanceDTOResult.getEmployee() = " + attendanceDTOResult.getEmployee());
    }

    @Test
    void attOffTest() {
        AttendanceDTO attendanceDTO = new AttendanceDTO();
        attendanceDTO.setId(1);
        attendanceDTO.setEmployee(1);
        attendanceService.attOff(attendanceDTO);
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

    @Test
    @DisplayName("근무 시간 = 퇴근 시간 - 출근 시간")
    void setDuration() {
        Attendance attendance = new Attendance();
        LocalDateTime attOn = LocalDateTime.of(1, 1, 1, 11, 50, 0);
        LocalDateTime attOff = LocalDateTime.of(1, 1, 1, 12, 50, 0);
        attendance.setAttOn(attOn);
        attendance.setAttOff(attOff);
    }

    @Test
    @DisplayName("durationTime Debugging")
    void debugDuration() {
        AttendanceDTO attendanceDTO = new AttendanceDTO();
        attendanceDTO.setId(1);
        attendanceDTO.setEmployee(1);
        attendanceService.attOff(attendanceDTO);
    }

    @Test
    @DisplayName("근태 정보 조회 상세")
    void attendDetail() {
        Integer id = 1;


    }
}