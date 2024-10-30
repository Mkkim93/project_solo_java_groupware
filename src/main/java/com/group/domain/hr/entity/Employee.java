package com.group.domain.hr.entity;

import com.group.application.hr.dto.EmployeeDTO;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

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

    @Column(name = "emp_isadmin") // roleType -> 차후 컬럼 및 변수명 수정
    private String empIsAdmin;

    @Column(name = "emp_no") // 사원 번호
    private String empNo;

    @Column(name = "emp_img") // 프로필 사진
    private String emp_img;

    @Column(name = "emp_tel") // 개인 연락처
    private String empTel;

    @Column(name = "emp_joindate") // 입사일
    private LocalDateTime empJoinDate;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "dept_id") // 실제 db Employee 테이블에 있는 department 의 외래 키
    private Department department;

    // 사원 등록 시점에 입사일 생성
    @PrePersist void JoinDate() {
        this.empJoinDate = LocalDateTime.now();
    }

    // 사원 정보 수정 시 dto -> entity 변환
    public Employee entityEmployee(EmployeeDTO employeeDTO) {
        this.id = employeeDTO.getId();
        this.userTel = employeeDTO.getUserTel();
        this.userEmail = employeeDTO.getUserEmail();
        return this;
    }
}
