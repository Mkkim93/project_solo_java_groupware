package com.group.application.hr.dto;

import com.group.domain.hr.entity.Employee;
import com.group.domain.hr.enums.EmpIsAdmin;
import com.group.domain.hr.enums.EmpJoinYN;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
public class EmployeeDTO {

    private Integer id;

    private String empPass;
    private String empName;
    private String empRank;
    private String empRegNo;
    private String empNickName;
    private String userTel;
    private String empEmail; // 로그인 계정
    private String userEmail;
    private Integer empMileage;
    private String empJoinYn;
    private String empIsAdmin;
    private String empNo;
    private Integer deptId;
    private Integer attId;

    public EmployeeDTO() {}

    // entity -> dto
    public EmployeeDTO(Employee employee) {
        empEmail = employee.getEmpEmail();
        empPass = employee.getEmpPass();
        empName = employee.getEmpName();
        empRank = employee.getEmpRank();
        empRegNo = employee.getEmpRegNo();
        empNickName = employee.getEmpNickname();
        userTel = employee.getUserTel();
        userEmail = employee.getUserEmail();
        empIsAdmin = employee.getEmpIsAdmin();
        empNo = employee.getEmpNo();
        deptId = employee.getDepartment().getId();
        attId = employee.getAttendance().getId();
    }
}
