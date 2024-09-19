package com.group.application.hr.service;

import com.group.application.hr.dto.AttendanceDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class AttendanceServiceTest {

    @Autowired
    AttendanceService attendanceService;

    @Test
    @DisplayName("근태 정보 생성")
    public void saveAttendance() {
        AttendanceDTO attendanceDTO = new AttendanceDTO();
        LocalDateTime now = LocalDateTime.now();
        attendanceDTO.setAttCreate(now);
        attendanceService.saveAttendance(attendanceDTO);
    }
}