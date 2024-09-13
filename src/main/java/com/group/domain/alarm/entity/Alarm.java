package com.group.domain.alarm.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.*;

@Entity
@Table(name = "alarm")
public class Alarm {

    public Alarm() {
    }

    @Id @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    @Column(name = "alarm_title")
    private String alarmTitle;

    @Column(name = "alarm_message")
    private String alarmMessage;

    @Column(name = "alarm_type")
    private String alarmType;

    @Column(name = "alarm_create")
    private LocalDateTime alarmCreate;

    @Column(name = "alarm_status")
    private String alarmStatus;
}
