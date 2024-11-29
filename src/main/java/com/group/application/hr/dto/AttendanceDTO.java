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

    public AttendanceDTO toDto(Attendance e) {
        this.id = e.getId();
        this.attOn = e.getAttOn();
        this.attOff = e.getAttOff();
        this.attDuration = e.getAttDuration();
        this.employee = e.getEmployee().getId();
        return this;
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
}
