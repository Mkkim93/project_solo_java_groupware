package com.group.application.hr.service;

import com.group.application.hr.dto.AttendanceDTO;
import com.group.application.hr.dto.EmployeeDTO;
import com.group.domain.hr.entity.Attendance;
import com.group.domain.hr.entity.Employee;
import com.group.domain.hr.repository.AttendanceRepository;
import com.group.domain.hr.repository.EmployeeRepositoryImpl;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.*;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAdjusters;
import java.util.Optional;

@Slf4j
@Service
@Transactional
public class AttendanceService {

    private final LocalDateTime checkedTime = LocalDateTime.of(LocalDate.now(), LocalTime.of(9, 0));
    private final Long WORK_HOURS_PER_DAY = 8L;

    private final AttendanceRepository attendanceRepository;
    private final EmployeeRepositoryImpl employeeRepositoryImpl;

    @Autowired
    public AttendanceService(AttendanceRepository attendanceRepository,
                             EmployeeRepositoryImpl employeeRepositoryImpl) {
        this.attendanceRepository = attendanceRepository;
        this.employeeRepositoryImpl = employeeRepositoryImpl;
    }

    public AttendanceDTO findByIdAttInfo(EmployeeDTO employeeDTO) {
        return employeeRepositoryImpl.findByOneEmpAttInfo(employeeDTO.getId());
    }

    // 출근 시간 로직
    public AttendanceDTO workIn(AttendanceDTO attendanceDTO) {

        LocalDate today = LocalDate.now(); // 오늘 날짜를 가져온다
        LocalDateTime startOfToday = today.atStartOfDay(); // 오늘의 시작 시간(00:00:00) 을 설정
        LocalDateTime endOfToday = today.atTime(LocalTime.MAX); // 오늘의 종료 시간(23:59:59)을 설정

        // employee id 를 기준으로 기존 출근 기록 조회 (오늘의 시작시간 between 종료시간 사이 기록 조회)
        Optional<Attendance> existAtt = attendanceRepository.findByEmployeeIdAndAttOnBetween(
                attendanceDTO.getEmployee(), startOfToday, endOfToday
        );

        Attendance attendance;
        if (existAtt.isPresent()) { // Optional 객체의 값이 있으면 true, 없으면 false
            // TODO 뷰에서 출근기록이 있으면 alert("이미 출근 상태입니다") 또는 버튼 display:none; 으로 바꿀지 고민
            attendance = existAtt.get();
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

        Attendance savedAtt = attendanceRepository.save(attendance);

        if (attendance.getAttOn().isAfter(checkedTime)) { // 출근 시간 ex) 09시 00분 보다 늦으면 지각 count 증가
            attendanceRepository.updatePerceptionCount(attendance.getId());
        }
        return attendanceDTO.toDto(savedAtt);
    }

    // 퇴근 시간 로직
    public AttendanceDTO workOut(AttendanceDTO attendanceDTO) {

        LocalDate today = LocalDate.now();
        LocalDateTime startOfToday = today.atStartOfDay();
        LocalDateTime endOfToday = today.atTime(LocalTime.MAX);

        Attendance attendance = attendanceRepository.findByEmployeeIdAndAttOnBetween(
                attendanceDTO.getEmployee(), startOfToday, endOfToday)
                .orElseThrow(() -> new EntityNotFoundException("no id"));

        if (attendance.getId() == null) {
            log.info("no id");
            // TODO EXCEPTION
            return null;
        } else {
            attendance.setAttOff(LocalDateTime.now());
            attendance.setEmployee(Employee.builder()
                            .id(attendanceDTO.getEmployee())
                    .build());
            Attendance savedAtt = attendanceRepository.save(attendance);
            savedDurationTime(savedAtt);
            log.info("existingAttendance.getId={}", attendance.getId());
            return attendanceDTO.toDto(savedAtt);
        }
    }

    // 근무 시간(Duration 계산) - 출근 시간 ~ 퇴근 시간
    private void savedDurationTime(Attendance attendance) {
        Long attDuration = setDuration(attendance);
        attendanceRepository.updateAttendanceByDuration(attDuration, attendance.getId());
    }

    public Long setDuration(Attendance attendance) {
        Duration duration = Duration.between(attendance.getAttOn(), attendance.getAttOff());
        return duration.toMinutes();
    }

    public Page<AttendanceDTO> findAllByEmpAttInfo(AttendanceDTO attendanceDTO,
                                                   LocalDate localDate,
                                                   PageRequest pageRequest) {
       return employeeRepositoryImpl.findByAllEmpAttInfo(attendanceDTO
               .getEmployee(), localDate, pageRequest);
    }

    /** 주간 근무 시간 계산 로직
     * 1. 이번주 누적 근무 시간
     * 2. 이번주 초과 근무 시간
     * 3. 이번주 잔여 근무 시간
     */
    public AttendanceDTO findByWeekOfMonthLogic(AttendanceDTO attendanceDTO) {
        Attendance attendance = attendanceRepository.findById(attendanceDTO.getEmployee())
                .orElseThrow(() -> new EntityNotFoundException("no id"));

        attendanceDTO.setEmployee(attendance.getEmployee().getId());

        // 현재 요일이 포함된 주차를 구하는 메서드
        LocalDate today = LocalDate.now();
        int day = today.get(ChronoField.DAY_OF_WEEK);

        if (day == 7) {
            day = 0;
        }

        LocalDate startDay = today.minusDays(day);
        LocalDate endDay = startDay.plusDays(6);

        return attendanceRepository
                .getAttendanceByOfWeekDuration(attendanceDTO.getEmployee(), startDay, endDay);
    }

    /**
     * 월간 근무 시간 계산 로직
     * 1. 이번달 누적 근무 시간
     * 2. 이번달 연장(초과) 근무 시간
     */
    public AttendanceDTO findByMonthDurationSum(AttendanceDTO attendanceDTO) {
        Integer month = attendanceDTO.getAttDate().getMonthValue();
        Integer year = attendanceDTO.getAttDate().getYear();
        // TODO 사원의 월 근무시간 총합
        return attendanceRepository.getAttendanceByOfMonthDuration(
                attendanceDTO.getEmployee(), year, month);
    }

    // 주말을 제외한 남은 근무 시간 계산 로직
    public Long remainingWorkHoursToWeek(AttendanceDTO attendanceDTO) {
        LocalDate today = attendanceDTO.getAttDate(); // 현재 직원의 데이터를 등록한 시점의 날짜를 통해 남은 근무 시간을 계산
        DayOfWeek todayDayOfWeek = today.getDayOfWeek();

        // 주말인 경우 다음 월요일부터 계산 시작
        if (todayDayOfWeek == DayOfWeek.SATURDAY || todayDayOfWeek == DayOfWeek.SUNDAY) {
            today = today.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
            todayDayOfWeek = today.getDayOfWeek();
        }

        Long remainingHours = 0L;

        if (todayDayOfWeek.getValue() <= DayOfWeek.FRIDAY.getValue()) {
            LocalTime now = LocalTime.now();
            LocalTime endOfWorkDay = LocalTime.of(18, 0);
            if (now.isBefore(endOfWorkDay)) {
                remainingHours += WORK_HOURS_PER_DAY - now.getHour() + 9;
            }

            // 오늘을 제외한 잔여 평일 근무 시간
            for (LocalDate date = today.plusDays(1); date.getDayOfWeek().getValue() <= 5; date = date.plusDays(1)) {
                remainingHours += WORK_HOURS_PER_DAY;
            }
        }
        return remainingHours;
    }
}
