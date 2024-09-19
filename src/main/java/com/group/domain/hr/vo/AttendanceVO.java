package com.group.domain.hr.vo;

import lombok.Getter;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
public class AttendanceVO {

    private Integer id;
    private LocalDateTime attOn;
    private LocalDateTime attOff;
    private Integer attPerception;
    private Integer attLeave;
    private Integer attVacation;
    private Date attDate;
    private LocalDateTime attCreate;

}
