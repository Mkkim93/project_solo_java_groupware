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
    public Attendance saveAttendance(AttendanceDTO newAttDto) {
        Attendance attendance = createAttendance(newAttDto);
        return attendanceRepository.save(attendance);
    }

    // attendance 생성 메서드
    public Attendance createAttendance(AttendanceDTO newAttDto) {
        Attendance attendance = new Attendance();
        LocalDateTime now = LocalDateTime.now();
        newAttDto.setAttCreate(now);
        attendance.setAttCreate(newAttDto.getAttCreate());
        return attendance;
    }
}
