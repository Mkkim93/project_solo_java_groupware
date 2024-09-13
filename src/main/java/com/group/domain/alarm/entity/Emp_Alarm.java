package com.group.domain.alarm.entity;

import com.group.domain.alarm.enums.IsRead;
import com.group.domain.employee.entity.Employee;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "emp_alarm")
public class Emp_Alarm {

    public Emp_Alarm() {
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    @Column(name = "isread")
    @Enumerated(EnumType.ORDINAL)
    private IsRead isRead;

    @Column(name = "receivedtime")
    private LocalDateTime receivedTime;


    @ManyToOne
    @JoinColumn(name = "employeeId")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "alarmId")
    private Alarm alarm;
}
