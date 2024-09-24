package com.group.application.hr.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DepartmentDTO {

    private Integer id;
    private String deptCode;
    private String deptName;

    public DepartmentDTO(Integer id) {
        this.id = id;
    }

    public DepartmentDTO(String deptCode, String deptName) {
        this.deptCode = deptCode;
        this.deptName = deptName;
    }


    public DepartmentDTO(Integer id, String deptCode, String deptName) {
        this.id = id;
        this.deptCode = deptCode;
        this.deptName = deptName;
    }
}
