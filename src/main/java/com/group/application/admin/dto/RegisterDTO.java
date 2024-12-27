package com.group.application.admin.dto;

import com.group.domain.hr.entity.Department;
import com.group.domain.hr.entity.Employee;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;

/**
 * RegisterDTO : 관리자 권한 - 사원 등록 및 정보 수정에 필요한 컬럼
 * empMail : 로그인 계정
 * empPass : 비밀번호
 * empName : 사원명
 * empRegNo : 주민등록번호
 * empRank : 직급
 * empTel : 연락처(회사)
 * empHireDate : 입사일
 * empNo : 사원번호
 * empGender : 성별 (남 : MALE, 여 : FEMALE)
 * empImg : 프로필 사진
 * departId : 부서 정보 (department pk)
 */
@Data
@NoArgsConstructor
public class RegisterDTO {

    private String empEmail;
    private String empPass;
    private String empName;
    private String empRegNo;
    private String empRank;
    private String empTel;
    private LocalDateTime empHireDate;
    private String empNo;
    private String empImg;
    private Integer departId;

    public RegisterDTO(String empEmail, String empPass, String empName,
                       String empRegNo, String empRank, String empTel,
                       LocalDateTime empHireDate, String empNo,
                       String empImg, Integer departId) {
        this.empEmail = empEmail;
        this.empPass = empPass;
        this.empName = empName;
        this.empRegNo = empRegNo;
        this.empRank = empRank;
        this.empTel = empTel;
        this.empHireDate = empHireDate;
        this.empNo = empNo;
        this.empImg = empImg;
        this.departId = departId;
    }

    public Employee toEmployeeEntity(RegisterDTO registerDto) {

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        return Employee.builder()
                .empEmail(registerDto.getEmpEmail())
                .empPass(passwordEncoder.encode(registerDto.getEmpPass()))
                .empName(registerDto.getEmpName())
                .empRegNo(registerDto.getEmpRegNo())
                .empRank(registerDto.getEmpRank())
                .empTel(registerDto.getEmpTel())
                .empHireDate(registerDto.getEmpHireDate())
                .empNo(registerDto.getEmpNo())
                .empImg(registerDto.getEmpImg())
                .department(Department.builder()
                        .id(registerDto.getDepartId())
                        .build())
                .build();
    }
}
