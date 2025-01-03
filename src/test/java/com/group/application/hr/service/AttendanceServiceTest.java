package com.group.application.hr.service;

import com.group.application.hr.dto.AttendanceDTO;
import com.group.application.hr.dto.EmployeeDTO;
import com.group.domain.hr.entity.Attendance;
import com.group.domain.hr.repository.AttendanceRepository;
import com.group.domain.hr.repository.EmployeeRepositoryImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootTest
class AttendanceServiceTest {

    @Autowired
    private AttendanceService attendanceService;

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private EmployeeRepositoryImpl employRepository;

    /*@Test
    void attOnTest() {
        AttendanceDTO attendanceDTO = new AttendanceDTO();
        attendanceDTO.setEmployee(1);

        AttendanceDTO attendanceDTOResult = attendanceService.att(attendanceDTO);

        System.out.println("attendanceDTOResult.getAttOn() = " + attendanceDTOResult.getAttOn());
        System.out.println("attendanceDTOResult.getEmployee() = " + attendanceDTOResult.getEmployee());
    }
*/
    /*@Test
    void attOffTest() {
        AttendanceDTO attendanceDTO = new AttendanceDTO();
        attendanceDTO.setId(1);
        attendanceDTO.setEmployee(1);
        attendanceService.attOff(attendanceDTO);
    }*/

    @Test
    void findById() {
        AttendanceDTO attendanceDTO = new AttendanceDTO();
        attendanceDTO.setId(1);
        Attendance attendance = attendanceRepository.findById(attendanceDTO.getEmployee()).get();
        // attendanceService.attOn(attendanceDTO);
        System.out.println("attendance.getId() = " + attendance.getId());
        System.out.println("attendance.getEmployee() = " + attendance.getEmployee());
        System.out.println("attendance.getAttOn() = " + attendance.getAttOn());
    }

    @Test
    @DisplayName("사원 정보로 근태 정보 ID 확인")
    void findByIdGetEmployeeAttId() {

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

    /*@Test
    @DisplayName("durationTime Debugging")
    void debugDuration() {
        AttendanceDTO attendanceDTO = new AttendanceDTO();
        attendanceDTO.setId(1);
        attendanceDTO.setEmployee(1);
        attendanceService.attOff(attendanceDTO);
    }*/

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

    @Test
    @DisplayName("임시 테스트 근태 상세 현황 오류 원인 찾기")
    void attendanceMonthServiceLogic() {
        AttendanceDTO attendanceDTO = new AttendanceDTO();
        attendanceDTO.setEmployee(1);
        attendanceDTO.setAttDate(LocalDate.now());
        AttendanceDTO byWeekWorkTime = attendanceService.findByWeekOfMonthLogic(attendanceDTO);
        System.out.println("byWeekWorkTime.getAttDate() = " + byWeekWorkTime.getAttDate());
        System.out.println("byWeekWorkTime.getEmployee() = " + byWeekWorkTime.getEmployee());
        System.out.println("byWeekWorkTime.getId() = " + byWeekWorkTime.getId());
    }

    @Test
    @DisplayName("이번달 근무 시간 총합")
    void findByMonthWorkTimeSum() {
        AttendanceDTO attendanceDTO = new AttendanceDTO();
        attendanceDTO.setEmployee(1);
        attendanceDTO.setAttDate(LocalDate.now());
        AttendanceDTO byMonthDurationSum = attendanceService.findByMonthDurationSum(attendanceDTO);
        System.out.println("byMonthDurationSum.getAttDuration() = " + byMonthDurationSum.getAttDuration());
    }

    @Test
    @DisplayName("이번주 잔여 근무 시간 합계")
    void findByWeekWorkTimeRemain() {
        AttendanceDTO attendanceDTO = new AttendanceDTO();
        attendanceDTO.setEmployee(1);
        attendanceDTO.setAttDate(LocalDate.now());
        attendanceService.remainingWorkHoursToWeek(attendanceDTO);
    }

    @Test
    @DisplayName("이번주 누적 근무시간 and 연장 근무 시간")
    void findByWeekWorkTimeDurationAndOverDuration() {
        AttendanceDTO attendanceDTO = new AttendanceDTO();
        attendanceDTO.setEmployee(1);
        attendanceDTO.setAttDate(LocalDate.now());
        attendanceService.findByWeekOfMonthLogic(attendanceDTO);
    }

    @Test
    @DisplayName("2024.12.06 weekWork Test")
    void weekTest() {
        AttendanceDTO attendanceDTO = new AttendanceDTO();
        attendanceDTO.setEmployee(29);
        AttendanceDTO result = attendanceService.findByWeekOfMonthLogic(attendanceDTO);
        System.out.println("result.getId() = " + result.getId());
        System.out.println("result.getEmployee() = " + result.getEmployee());
        System.out.println("result.getAttDuration() = " + result.getAttDuration());
        System.out.println("result.getAttPerception() = " + result.getAttPerception());
    }

    @Test
    void findByIdAttInfo() {
        EmployeeDTO dto = new EmployeeDTO();
        dto.setId(29);
        dto.setEmpUUID("6103a7be-69ac-47f4-89ee-267997f87a64");
        AttendanceDTO result = attendanceService.findByIdAttInfo(dto);
        /*System.out.println("result.getId() = " + result.getId());*/
        System.out.println("result.getEmployee() = " + result.getEmployee());
        System.out.println("result.getAttOn() = " + result.getAttOn());
    }

    @Test
    void findByOneEmpAttInfo() {
        employRepository.findByOneEmpAttInfo(29);
        EmployeeDTO dto = new EmployeeDTO();
    }
}