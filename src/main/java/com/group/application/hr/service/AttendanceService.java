package com.group.application.hr.service;

import com.group.application.hr.dto.AttendanceDTO;
import com.group.application.hr.dto.EmployeeDTO;
import com.group.domain.hr.entity.Attendance;
import com.group.domain.hr.entity.Employee;
import com.group.domain.hr.repository.AttendanceRepository;
import com.group.domain.hr.repository.EmployRepositoryImpl;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.*;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final EmployRepositoryImpl employRepositoryImpl;

    @Autowired
    public AttendanceService(AttendanceRepository attendanceRepository,
                             EmployRepositoryImpl employRepository) {
        this.attendanceRepository = attendanceRepository;
        this.employRepositoryImpl = employRepository;
    }

    public AttendanceDTO findByIdAttInfo(EmployeeDTO employeeDTO) {
        return employRepositoryImpl.findByOneEmpAttInfo(employeeDTO.getId());
    }

    // TODO 나중에 다시 코드 이해..
    public AttendanceDTO attOn(AttendanceDTO attendanceDTO) {

        LocalDate today = LocalDate.now(); // 오늘 날짜를 가져온다
        LocalDateTime startOfToday = today.atStartOfDay(); // 오늘의 시작 시간(00:00:00) 을 설정
        LocalDateTime endOfToday = today.atTime(LocalTime.MAX); // 오늘의 종료 시간(23:59:59)을 설정

        // employee id 를 기준으로 기존 출근 기록 조회 (오늘의 시작시간 between 종료시간 사이 기록 조회)
        Optional<Attendance> existingAttendance = attendanceRepository.findByEmployeeIdAndAttOnBetween(
                attendanceDTO.getEmployee(), startOfToday, endOfToday
        );

        Attendance attendance;
        if (existingAttendance.isPresent()) { // Optional 객체의 값이 있으면 true, 없으면 false
            // TODO 뷰에서 출근기록이 있으면 alert("이미 출근 상태입니다") 또는 버튼 display:none; 으로 바꿀지 고민
            attendance = existingAttendance.get();
            attendance.setAttOn(LocalDateTime.now());
        } else { // 값이 없으면 저장
            attendance = Attendance.builder()
                    .attOn(LocalDateTime.now())
                    .attDate(LocalDate.now()) // 출근 처리 후 오늘 날짜를 저장
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
        LocalDate today = LocalDate.now();

        Attendance attOnDTO = attendanceRepository.findById(attendanceDTO.getId())
                .orElseThrow(() -> new EntityNotFoundException("not id"));

        boolean existsById = attendanceRepository.existsById(attendanceDTO.getId());

        if (!existsById) {
            new RuntimeException("not id");
        }
            Attendance attendance = Attendance.builder()
                    .id(attendanceDTO.getId())
                    .attOn(attOnDTO.getAttOn())
                    .attOff(LocalDateTime.now())
                    .attDate(attOnDTO.getAttDate())
                    .employee(Employee.builder()
                            .id(attendanceDTO.getEmployee())
                            .build())
                    .build();
        Attendance savedAttendance = attendanceRepository.save(attendance);

        log.info("savedAttendance.getAttOn={}", savedAttendance.getAttOn());
        log.info("savedAttendance.getAttOff={}", savedAttendance.getAttOff());

        savedDurationTime(savedAttendance);
        return attendanceDTO.converterDTO(savedAttendance);
    }

    private void savedDurationTime(Attendance attendance) {
        Long attDuration = setDuration(attendance);
        attendanceRepository.updateAttendanceByDuration(attDuration, attendance.getId());
    }
    public Long setDuration(Attendance attendance) {
        Duration duration = Duration.between(attendance.getAttOn(), attendance.getAttOff());
        return duration.toMinutes();
    }

    public List<AttendanceDTO> findAllByEmpAttInfo(AttendanceDTO attendanceDTO, LocalDate localDate) {
       return employRepositoryImpl.findByAllEmpAttInfo(attendanceDTO.getEmployee(), localDate);
    }
}
