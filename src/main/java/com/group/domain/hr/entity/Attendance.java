package com.group.domain.hr.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

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

    @Column(name = "att_overduration") // 연장 근무 시간
    private Long attOverDuration;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "emp_id")
    private Employee employee;

    @PrePersist void setEmpAtt() {
        this.attPerception = 0L;
        // TODO 나머지 카운트로 초기값 설정할지 고민
    }

}