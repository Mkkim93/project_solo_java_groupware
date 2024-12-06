package com.group.domain.hr.entity;

import com.group.application.hr.dto.EmployeeDTO;
import com.group.domain.mail.entity.MailBox;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static jakarta.persistence.FetchType.*;
import static jakarta.persistence.GenerationType.IDENTITY;
import static jakarta.persistence.GenerationType.UUID;

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

    @Column(name = "role_type")
    private String roleType;

    @Column(name = "emp_no") // 사원 번호
    private String empNo;

    @Column(name = "emp_img") // 프로필 사진
    private String empImg;

    @Column(name = "emp_tel") // 개인 연락처
    private String empTel;

    @Column(name = "emp_joindate") // 입사일
    private LocalDateTime empJoinDate;

    @Column(name = "emp_uuid", length = 36) // 본인계정의 데이터 조회시 사용되는 컬럼
    private String empUUID;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "dept_id") // 실제 db Employee 테이블에 있는 department 의 외래 키
    private Department department;

    // MailBox 엔티티와의 다대다 관계 설정 (수신한 메일)
    @ManyToMany(mappedBy = "receiverEmployees", fetch = FetchType.LAZY)
    private List<MailBox> receivedMailBoxes = new ArrayList<>();

    // MailBox 엔티티와의 일대다 관계 설정 (보낸 메일)
    @OneToMany(mappedBy = "senderEmployee", fetch = FetchType.LAZY)
    private List<MailBox> sentMailBoxes = new ArrayList<>();

    // 사원 등록 시점에 생성되는 데이터
    // 현재 날짜, 사원으 uuid (pk 대신 검증하기 위한 조건 데이터)
    @PrePersist void createInfo() {
        this.empJoinDate = LocalDateTime.now();
        this.empUUID = java.util.UUID.randomUUID().toString();
        this.roleType = "USER";
    }
}
