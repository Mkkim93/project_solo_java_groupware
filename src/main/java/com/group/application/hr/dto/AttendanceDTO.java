package com.group.application.hr.dto;

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

    public AttendanceDTO(Integer id) {
        this. id= id;
    }
}
