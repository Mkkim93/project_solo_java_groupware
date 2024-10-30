package com.group.application.hr.service;

import com.group.application.hr.dto.AttendanceDTO;
import com.group.domain.hr.entity.Attendance;
import com.group.domain.hr.entity.Employee;
import com.group.domain.hr.repository.AttendanceRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

@Service
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;

    public AttendanceService(AttendanceRepository attendanceRepository) {
        this.attendanceRepository = attendanceRepository;
    }

    // TODO 나중에 다시 코드 이해..
    public AttendanceDTO attOn(AttendanceDTO attendanceDTO) {

        LocalDate today = LocalDate.now();
        LocalDateTime startOfToday = today.atStartOfDay();
        LocalDateTime endOfToday = today.atTime(LocalTime.MAX);

        Optional<Attendance> existingAttendance = attendanceRepository.findByEmployeeIdAndAttOnBetween(
                attendanceDTO.getEmployee(), startOfToday, endOfToday
        );

        Attendance attendance;
        if (existingAttendance.isPresent()) {

            attendance = existingAttendance.get();
            attendance.setAttOn(LocalDateTime.now());
        } else {
            attendance = Attendance.builder()
                    .attOn(LocalDateTime.now())
                    .employee(Employee.builder()
                            .id(attendanceDTO.getEmployee())
                            .build())
                    .build();
        }
        Attendance savedAttendance = attendanceRepository.save(attendance);

        // TODO 출근 시간보다 늦으면 지각처리 로직 구현

        return attendanceDTO.converterDTO(savedAttendance);
    }

    public AttendanceDTO attOff(AttendanceDTO attendanceDTO) {
        Attendance attendance = Attendance.builder()
                .attOff(LocalDateTime.now())
                .employee(Employee.builder()
                        .id(attendanceDTO.getEmployee())
                        .build())
                .build();
        Attendance savedAttendance = attendanceRepository.save(attendance);
        return attendanceDTO.converterDTO(savedAttendance);
    }
}
