package com.group.application.hr.dto;

import com.group.domain.hr.entity.Attendance;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class AttendanceDTO {

    private Integer id;
    private LocalDateTime attOn;
    private LocalDateTime attOff;
    private Long attPerception;
    private Long attLeave;
    private Long attVacation;
    private LocalDate attDate;
    private LocalDateTime attCreate;
    private Long attDuration;

    private Long attRemain;
    private Long attOverDuration;

    private Integer employee;
    private String empName;

    private Long weekAttDuration;
    private Long weekOverDuration;
    private Long weekRemainDuration;

    // 오늘 하루의 데이터만 조회하기 oneDayOf
    @QueryProjection
    public AttendanceDTO(Integer id, LocalDateTime attOn, LocalDateTime attOff, Long attDuration, Long attOverDuration,
                         Long attPerception, Long attLeave, Long attVacation,
                         LocalDate attDate, LocalDateTime attCreate, Integer employee, String empName) {
        this.id = id;
        this.attOn = attOn;
        this.attOff = attOff;
        this.attDuration = attDuration;
        this.attOverDuration = attOverDuration;
        this.attPerception = attPerception;
        this.attLeave = attLeave;
        this.attVacation = attVacation;
        this.attDate = attDate;
        this.attCreate = attCreate;
        this.empName = empName;
        this.employee = employee;
    }

    public AttendanceDTO converterDTO(Attendance attendance) {
        this.id = attendance.getId();
        this.attOn = attendance.getAttOn();
        this.attOff = attendance.getAttOff();
        this.attDuration = attendance.getAttDuration();
        this.employee = attendance.getEmployee().getId();
        return this;
    }

    // TODO 나중에 안쓸수도 있음
    public AttendanceDTO(LocalDateTime attOn, LocalDateTime attOff, Long attRemain, Long attOverDuration) {
        this.attOn = attOn;
        this.attOff = attOff;
        this.attRemain = attRemain;
        this.attOverDuration = attOverDuration;
    }

    // 초과근무시간과 잔여근무시간 계산
    public AttendanceDTO calculateOvertimeAndRemainingTime(Attendance attendance) {
        Long basicTime = 8L;
        Long duration = attendance.getAttDuration();

        Long overTime = Math.max(0, duration - basicTime * 60);
        Long remainingTime = Math.max(0, basicTime * 60 - duration);

        return new AttendanceDTO(attendance.getAttOn(), attendance.getAttOff(), overTime, remainingTime);
    }

    // 월별 근태 조회를 위한 Projections DTO 생성
    public AttendanceDTO(Long attDuration, Long attOverDuration) {
        this.attDuration = attDuration;
        this.attOverDuration = attOverDuration;
    }

    public AttendanceDTO(Long attPerception, Long attLeave, Long attVacation, Long attOverDuration) {
        this.attPerception = attPerception;
        this.attLeave = attLeave;
        this.attVacation = attVacation;
        this.attOverDuration = attOverDuration;
    }

    public AttendanceDTO toAttendanceDTO(Attendance attendance) {
        this.id = attendance.getId();
        this.attDate = attendance.getAttDate();
        this.employee = attendance.getEmployee().getId();
        return this;
    }
}
