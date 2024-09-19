package com.group.application.hr.dto;

import com.group.domain.hr.enums.EmpIsAdmin;
import com.group.domain.hr.enums.EmpJoinYN;
import lombok.Data;

@Data
public class EmployeeDTO {

    private Integer id;
    private String empPass;
    private String empName;
    private String empRank;
    private String empRegNo;
    private String empNickName;
    private String userTel;
    private String empEmail;
    private String userEmail;
    private Integer empMileage;
    private EmpJoinYN empJoinYn;
    private EmpIsAdmin empIsAdmin;
    private String empNo;

    private Integer deptId;
    private Integer attId;
}
