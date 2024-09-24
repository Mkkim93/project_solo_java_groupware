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

    /**
     *
     * @param id 근태 번호
     * COMMENT : 사원 등록 시 근태번호가 자동으로 생성되도록 로직 구현
     */
    public AttendanceDTO(Integer id) {
        this. id= id;
    }
}
