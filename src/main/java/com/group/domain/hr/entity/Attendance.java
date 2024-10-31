package com.group.domain.hr.entity;

import jakarta.persistence.*;
import lombok.*;

import javax.swing.*;
import java.time.Duration;
import java.time.LocalDate;
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

    @Column(name = "att_on", updatable = true)
    private LocalDateTime attOn; // 출근 시간

    @Column(name = "att_off")
    private LocalDateTime attOff; // 퇴근 시간

    @Column(name = "att_perception")
    private Long attPerception; // 지각

    @Column(name = "att_leave")
    private Long attLeave; // 조퇴

    @Column(name = "att_vacation")
    private Long attVacation; // 휴가

    @Column(name = "att_date")
    private LocalDate attDate; // 월별 집계

    @Column(name = "att_create")
    private LocalDateTime attCreate;

    @Column(name = "att_duration") // 총 근무 시간
    private Long attDuration;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "emp_id")
    private Employee employee;

}