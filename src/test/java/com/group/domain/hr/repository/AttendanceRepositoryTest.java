package com.group.domain.hr.repository;

import com.group.application.hr.dto.AttendanceDTO;
import com.group.domain.hr.entity.Attendance;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class AttendanceRepositoryTest {

    @Autowired
    AttendanceRepository attendanceRepository;

    @Test
    @DisplayName("사원 id 로 근태 정보 조회")
    void findByEmpIdAttId() {
        AttendanceDTO attendanceDTO = new AttendanceDTO();
        attendanceDTO.setEmployee(1);
        attendanceRepository.findById(attendanceDTO.getEmployee());
        /*System.out.println("attendance.getId() = " + attendance.getId());
        // System.out.println("attendance.getEmployee() = " + attendance.getEmployee().getId());
        System.out.println("attendance.getClass() = " + attendance.getClass());*/
    }

}