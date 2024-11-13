package com.group.application.hr.service;

import com.group.application.hr.dto.AttendanceDTO;
import com.group.domain.hr.entity.Attendance;
import com.group.domain.hr.repository.AttendanceRepository;
import com.group.domain.hr.repository.EmployRepositoryImpl;
import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.time.temporal.IsoFields;
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
    @DisplayName("지각 시 지각 attPerception + 1 증가")
    void countPerception() {
        AttendanceDTO attendanceDTO = new AttendanceDTO();
        attendanceDTO.setId(1);
        attendanceDTO.setEmployee(1);
        attendanceRepository.updatePerceptionCount(attendanceDTO.getId());
    }

    @Test
    @DisplayName("근태 정보 조회 상세 (페이징으로 주단위 구분)")
    void attendDetail() {
        Integer id = 1;
        AttendanceDTO attendanceDTO = new AttendanceDTO();
        attendanceDTO.setEmployee(id);
        attendanceDTO.setAttDate(LocalDate.now());
        PageRequest pageRequest = PageRequest.of(0, 5);
        Page<AttendanceDTO> result = attendanceService.findAllByEmpAttInfo(attendanceDTO, attendanceDTO.getAttDate(), pageRequest);

        for (AttendanceDTO dto : result) {
            System.out.println("empId = " + dto.getEmployee());
            System.out.println("dto.getEmpName = " + dto.getEmpName());
            System.out.println("dto.getId = " + dto.getId());
            System.out.println("dto.getAttOn() = " + dto.getAttOn());
            System.out.println("dto.getAttOff() = " + dto.getAttOff());
        }
    }

    @Test
    @DisplayName("월 근무시간 계산")
    void empAttDurationMonthAvg() {
        AttendanceDTO attendanceDTO = new AttendanceDTO();
        attendanceDTO.setEmployee(1);

        LocalDate now = LocalDate.now();
        Integer month = now.getMonthValue();
        Integer year = now.getYear();

        AttendanceDTO attDurationMonthSum = attendanceRepository.getAttendanceByOfMonthDuration(attendanceDTO.getEmployee(), year, month);

        System.out.println("attDurationMonthSum = " + attDurationMonthSum);
    }

    @Test
    @DisplayName("해당 월 주차의 이벤트 통계(지각, 반차, 휴가, 야근횟수)")
    void empAttDurationWeekSum() {
        AttendanceDTO attendanceDTO = new AttendanceDTO();
        attendanceDTO.setEmployee(1);
        LocalDate start = LocalDate.of(2024, 11, 11);
        LocalDate end = LocalDate.of(2024, 11, 15);
        AttendanceDTO attendanceByOfWeekInfo = attendanceRepository.rangeOfAttendanceByWeek(attendanceDTO.getEmployee(), start, end);
        System.out.println("attendanceByOfWeekInfo.getAttOverDuration() = " + attendanceByOfWeekInfo.getAttOverDuration());
        System.out.println("attendanceByOfWeekInfo.getAttPerception() = " + attendanceByOfWeekInfo.getAttPerception());
        System.out.println("attendanceByOfWeekInfo.getAttVacation() = " + attendanceByOfWeekInfo.getAttVacation());
        System.out.println("attendanceByOfWeekInfo.getAttLeave() = " + attendanceByOfWeekInfo.getAttLeave());
    }

}