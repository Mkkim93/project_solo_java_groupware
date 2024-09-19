package com.group.domain.hr.vo;

import com.group.domain.hr.enums.EmpIsAdmin;
import com.group.domain.hr.enums.EmpJoinYN;
import lombok.Getter;

@Getter
public class EmployeeVO {

    private Integer id;
    private String empPass;
    private String empName;
    private String empRank;
    private String empRegNo;
    private String empNickname;
    private String userTel;
    private String empEmail;
    private String userEmail;
    private Integer empMileage;
    private EmpIsAdmin empJoinYN;
    private EmpJoinYN empIsAdmin;
    private Integer deptId;
    private Integer attId;
}
