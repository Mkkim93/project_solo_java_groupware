package com.group.domain.hr.entity;

import com.group.application.hr.dto.EmployeeDTO;
import com.group.domain.mail.entity.MailBox;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static jakarta.persistence.EnumType.*;
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

    @Column(name = "emp_pass") // 로그인 비밀번호
    private String empPass;

    @Column(name = "emp_name") // 사원명
    private String empName;

    @Column(name = "emp_rank") // 직급
    private String empRank;

    @Column(name = "emp_regno") // 주민등록번호
    private String empRegNo;

    @Column(name = "personal_phone") // 개인 연락처 (HP)
    private String userTel;

    @Column(name = "emp_email") // 사내 메일 주소 (로그인 ID 로 사용)
    private String empEmail;

    @Column(name = "personal_email") // 개인 메일 주소
    private String userEmail;

    @Column(name = "emp_status") // 재직 상태 (Y, N)
    private String empJoinYN;

    @Column(name = "role") // 권한 (ROLE_USER, ROLE_ADMIN)
    private String roleType;

    @Column(name = "emp_no") // 사원 번호
    private String empNo;

    @Column(name = "emp_img") // 프로필 사진
    private String empImg;

    @Column(name = "emp_hiredate") // 입사일
    private LocalDateTime empHireDate;

    @Column(name = "emp_uuid", length = 36) // 본인계정의 데이터 조회시 사용되는 컬럼
    private String empUUID;

    @Column(name = "emp_terminationdate") // 퇴사일
    private LocalDateTime empTerminationDate;

    @Column(name = "emp_workphone") // 회사 내선번호
    private String empTel;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "dept_id")  // 부서 정보 (department PK)
    private Department department; // 실제 db Employee 테이블에 있는 department 의 외래 키

    // MailBox 엔티티와의 다대다 관계 설정 (수신한 메일)
    @ManyToMany(mappedBy = "receiverEmployees", fetch = FetchType.LAZY)
    private List<MailBox> receivedMailBoxes = new ArrayList<>();

    // MailBox 엔티티와의 일대다 관계 설정 (보낸 메일)
    @OneToMany(mappedBy = "senderEmployee", fetch = FetchType.LAZY)
    private List<MailBox> sentMailBoxes = new ArrayList<>();

    // 사원 등록 시점에 생성되는 데이터
    // 현재 날짜, 사원으 uuid (pk 대신 검증하기 위한 조건 데이터)
    @PrePersist void createInfo() {
        this.empUUID = java.util.UUID.randomUUID().toString();
    }

    public EmployeeDTO toDto(Employee e) {
        return new EmployeeDTO(e.getId(), e.getEmpUUID(), e.getRoleType(), e.getEmpUUID());
    }
}
