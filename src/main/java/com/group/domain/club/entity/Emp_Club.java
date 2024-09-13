package com.group.domain.club.entity;

import com.group.domain.employee.entity.Employee;
import jakarta.persistence.*;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "emp_club")
public class Emp_Club {

    public Emp_Club() {
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    @Column(name = "empclub_joindate")
    private LocalDateTime empClubJoinDate;

    @ManyToOne
    @JoinColumn(name = "empId")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "clubId")
    private Club club;
}
