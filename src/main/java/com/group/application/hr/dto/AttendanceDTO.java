package com.group.application.hr.dto;

import com.group.domain.hr.entity.Attendance;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
public class AttendanceDTO {

    private Integer id;
    private LocalDateTime attOn;
    private LocalDateTime attOff;
    private Integer attPerception;
    private Integer attLeave;
    private Integer attVacation;
    private Date attDate;
    private LocalDateTime attCreate;

    private String empName;
    private Integer employee;

    @QueryProjection
    public AttendanceDTO(Integer id, LocalDateTime attOn, LocalDateTime attOff,
                         Integer attPerception, Integer attLeave, Integer attVacation,
                         Date attDate, LocalDateTime attCreate, String empName, Integer employee) {
        this.id = id;
        this.attOn = attOn;
        this.attOff = attOff;
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
        return this;
    }
}
