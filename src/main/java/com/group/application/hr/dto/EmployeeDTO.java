package com.group.application.hr.dto;

import com.group.domain.hr.entity.Employee;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
public class EmployeeDTO {

    private Integer id;
    private String empEmail;
    private String empPass;
    private String empName;
    private String empRegNo;
    private String userEmail;
    private String empNickName;
    private String empRank;
    private String userTel;
    private String empNo;
    private Integer empMileage;
    private String empImg;
    private String empTel;
    private LocalDateTime empJoinDate;
    private Integer deptId;
    private Integer attId;

    @QueryProjection
    public EmployeeDTO(Integer id, String empEmail, String empPass,
                       String empName, String empRegNo, String userEmail,
                       String empNickName, String empRank, String userTel,
                       String empNo, Integer empMileage, String empImg,
                       String empTel, LocalDateTime empJoinDate, Integer deptId, Integer attId) {
        this.id = id;
        this.empEmail = empEmail;
        this.empPass = empPass;
        this.empName = empName;
        this.empRegNo = empRegNo;
        this.userEmail = userEmail;
        this.empNickName = empNickName;
        this.empRank = empRank;
        this.userTel = userTel;
        this.empNo = empNo;
        this.empMileage = empMileage;
        this.empImg = empImg;
        this.empTel = empTel;
        this.empJoinDate = empJoinDate;
        this.deptId = deptId;
        this.attId = attId;
    }

    // Entity -> DTO
    public EmployeeDTO setEmployeeDTO(Employee employee) {
        this.id = employee.getId();
        this.empName = employee.getEmpName();
        this.empRegNo = employee.getEmpRegNo();
        this.empRank = employee.getEmpRank();
        this.empNickName = employee.getEmpNickname();
        this.userTel = employee.getUserTel();
        this.userEmail = employee.getUserEmail();
        this.empEmail = employee.getEmpEmail();
        this.empMileage = employee.getEmpMileage();
        this.empNo = employee.getEmpNo();
        this.empTel = employee.getEmpTel();
        return this;
    }
}
