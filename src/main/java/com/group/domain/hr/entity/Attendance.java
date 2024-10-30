package com.group.domain.hr.entity;

import jakarta.persistence.*;
import lombok.*;

import javax.swing.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static jakarta.persistence.FetchType.*;
import static jakarta.persistence.GenerationType.*;

@Entity
@Table(name = "attendance")
@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Attendance {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    @Column(name = "att_on")
    private LocalDateTime attOn; // 출근 시간

    @Column(name = "att_off")
    private LocalDateTime attOff; // 퇴근 시간

    @Column(name = "att_perception")
    private Integer attPerception; // 지각

    @Column(name = "att_leave")
    private Integer attLeave; // 조퇴

    @Column(name = "att_vacation")
    private Integer attVacation; // 휴가

    @Column(name = "att_date")
    private Date attDate; // 월별 집계

    @Column(name = "att_create")
    private LocalDateTime attCreate;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "emp_id")
    private Employee employee;
}