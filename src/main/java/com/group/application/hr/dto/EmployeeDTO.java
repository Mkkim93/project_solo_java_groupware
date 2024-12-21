package com.group.application.hr.dto;

import com.group.domain.hr.entity.Employee;
import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.scheduling.support.SimpleTriggerContext;

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
    private String roleType;

    private String empUUID;

    @QueryProjection
    public EmployeeDTO(Integer id, String empEmail, String empPass,
                       String empName, String empRegNo, String userEmail,
                       String empNickName, String empRank, String userTel,
                       String empNo, Integer empMileage, String empImg,
                       String empTel, LocalDateTime empJoinDate, Integer deptId, Integer attId, String empUUID , String roleType) {
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
        this.empUUID = empUUID;
        this.roleType = roleType;
    }

    // Entity -> DTO
    public EmployeeDTO toDto(Employee e) {
        this.id = e.getId();
        this.empName = e.getEmpName();
        this.empRegNo = e.getEmpRegNo();
        this.empRank = e.getEmpRank();
        this.empNickName = e.getEmpNickname();
        this.userTel = e.getUserTel();
        this.userEmail = e.getUserEmail();
        this.empEmail = e.getEmpEmail();
        this.empMileage = e.getEmpMileage();
        this.empNo = e.getEmpNo();
        this.empTel = e.getEmpTel();
        this.roleType = e.getRoleType();
        this.empUUID = e.getEmpUUID();
        return this;
    }

    // login employee entity -> dto
    public EmployeeDTO(Employee e) {
        this.id = e.getId();
        this.empEmail = e.getEmpEmail();
        this.empPass = e.getEmpPass();
        this.empName = e.getEmpName();
        this.empRegNo = e.getEmpRegNo();
        this.userEmail = e.getUserEmail();
        this.empNickName = e.getEmpNickname();
        this.empRank = e.getEmpRank();
        this.userTel = e.getUserTel();
        this.empNo = e.getEmpNo();
        this.empMileage = e.getEmpMileage();
        this.empImg = e.getEmpImg();
        this.empTel = e.getEmpTel();
        this.empJoinDate = e.getEmpJoinDate();
        this.deptId = getDeptId();
        this.attId = getAttId();
        this.roleType = e.getRoleType();
        this.empUUID = e.getEmpUUID();
    }

    public EmployeeDTO(Integer id, String empEmail, String roleType, String empUUID) {
        this.id = id;
        this.empEmail = empEmail;
        this.roleType = roleType;
        this.empUUID = empUUID;
    }
}
