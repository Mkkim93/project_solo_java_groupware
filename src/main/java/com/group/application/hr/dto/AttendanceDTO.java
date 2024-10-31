package com.group.application.hr.dto;

import com.group.domain.hr.entity.Attendance;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.grammars.hql.HqlParser;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

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

    private Integer employee;
    private String empName;

    // 오늘 하루의 데이터만 조회하기 oneDayOf
    @QueryProjection
    public AttendanceDTO(Integer id, LocalDateTime attOn, LocalDateTime attOff, Long attDuration,
                         Long attPerception, Long attLeave, Long attVacation,
                         LocalDate attDate, LocalDateTime attCreate, Integer employee, String empName) {
        this.id = id;
        this.attOn = attOn;
        this.attOff = attOff;
        this.attDuration = attDuration;
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
}
