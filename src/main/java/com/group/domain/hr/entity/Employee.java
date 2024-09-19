package com.group.domain.hr.entity;

import com.group.application.hr.dto.EmployeeDTO;
import com.group.domain.hr.entity.Department;
import com.group.domain.hr.enums.EmpIsAdmin;
import com.group.domain.hr.enums.EmpJoinYN;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@Entity
@Table(name = "employee")
@NoArgsConstructor
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
    @Enumerated(EnumType.STRING)
    private EmpJoinYN empJoinYN;

    @Column(name = "emp_isadmin")
    @Enumerated(EnumType.STRING)
    private EmpIsAdmin empIsAdmin;

    @Column(name = "emp_no")
    private String empNo;

    @ManyToOne
    @JoinColumn(name = "dept_id") // 실제 db Employee 테이블에 있는 department 의 외래 키
    private Department department;

    @ManyToOne
    @JoinColumn(name = "att_id")
    private Attendance attendance;

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", empPass='" + empPass + '\'' +
                ", empName='" + empName + '\'' +
                ", empRank='" + empRank + '\'' +
                ", empRegNo='" + empRegNo + '\'' +
                ", empNickname='" + empNickname + '\'' +
                ", userTel='" + userTel + '\'' +
                ", empEmail='" + empEmail + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", empMileage=" + empMileage +
                ", empJoinYN=" + empJoinYN +
                ", empIsAdmin=" + empIsAdmin +
                ", departmentName=" + department.getDeptName() + ", departmentCode=" + department.getDeptCode() +
                '}';
    }
}
