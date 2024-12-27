package com.group.application.hr.dto;

import com.querydsl.core.annotations.QueryProjection;

public class EmpDepartDTO {

    private Integer empId;
    private String empName;
    private String empRank;
    private String deptName;

    public EmpDepartDTO(Integer empId, String empName,
                        String empRank, String deptName) {
        this.empId = empId;
        this.empName = empName;
        this.empRank = empRank;
        this.deptName = deptName;
    }
}
