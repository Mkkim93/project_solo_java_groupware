package com.group.domain.alarm.vo;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class AlarmVO {

    private Integer id;
    private String alarm_title;
    private String alarm_message;
    private String alarm_type;
    private LocalDateTime alarm_create;
    private String alarm_status;
}
