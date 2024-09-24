package com.group.application.hr.service;

import com.group.application.hr.dto.AttendanceDTO;
import com.group.domain.hr.entity.Attendance;
import com.group.domain.hr.repository.AttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AttendanceService {

    @Autowired
    AttendanceRepository attendanceRepository;

    /**
     * 근태 정보 생성
     * 생성된 근태 정보 id 와 saveEmployee() att_id 에 주입
     */
    public Attendance saveAttendance() {
        Attendance attendance = Attendance.builder()
                .attCreate(LocalDateTime.now())
                .build();
        return attendanceRepository.save(attendance);
    }
}
