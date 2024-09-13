package com.group.domain.attendance.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Date;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "attendance")
public class Attendance {

    public Attendance() {
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;


    @Column(name = "att_on")
    private LocalDateTime attOn;

    @Column(name = "att_off")
    private LocalDateTime attOff;

    @Column(name = "att_perception")
    private Integer attPerception; // 지각

    @Column(name = "att_leave")
    private Integer attLeave; // 조퇴

    @Column(name = "att_vacation")
    private Integer attVacation; // 휴가

    @Column(name = "att_date")
    private Date attDate; // 월별기록

    @Column(name = "att_create")
    private LocalDateTime attCreate;
}
