package com.group.domain.hr.entity;

import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.FetchType.*;
import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "employee")
@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    @Column(name = "emp_pass")
    private String empPass;

    @Column(name = "emp_name")
    private String empName;

    @Column(name = "emp_rank")
    private String empRank;

    @Column(name = "emp_regno")
    private String empRegNo;

    @Column(name = "emp_nickname")
    private String empNickname;

    @Column(name = "user_tel")
    private String userTel;

    @Column(name = "emp_email")
    private String empEmail;

    @Column(name = "user_email")
    private String userEmail;

    @Column(name = "emp_mileage")
    private Integer empMileage;

    @Column(name = "emp_joinyn")
    private String empJoinYN;

    @Column(name = "emp_isadmin")
    private String empIsAdmin;

    @Column(name = "emp_no")
    private String empNo;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "dept_id") // 실제 db Employee 테이블에 있는 department 의 외래 키
    private Department department;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "att_id")
    private Attendance attendance;
}
