package com.group.domain.hr.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

import static jakarta.persistence.GenerationType.*;

@Getter @Setter
@Entity
@Table(name = "attendance")
@NoArgsConstructor
public class Attendance {

    @Id @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    @Column(name = "att_on")
    private LocalDateTime attOn;

    @Column(name = "att_off")
    private LocalDateTime attOff;

    @Column(name = "att_perception")
    private Integer attPerception;

    @Column(name = "att_leave")
    private Integer attLeave;

    @Column(name = "att_vacation")
    private Integer attVacation;

    @Column(name = "att_date")
    private Date attDate;

    @Column(name = "att_create")
    private LocalDateTime attCreate;
}
