package com.group.domain.alarm.vo;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Emp_AlarmVO {

    private Integer id;
    private Boolean isRead;
    private LocalDateTime receivedTime;
    private Integer empId;
    private Integer alarmId;
}
