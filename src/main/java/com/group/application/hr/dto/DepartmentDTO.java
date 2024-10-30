package com.group.application.hr.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DepartmentDTO {

    private Integer id;
    private String deptCode;
    private String deptName;
    private String deptIsDeleted;

    private Integer empId;
    private String empName;

    @QueryProjection
    public DepartmentDTO(Integer id, String deptCode, String deptName, Integer empId, String empName) {
        this.id = id;
        this.deptCode = deptCode;
        this.deptName = deptName;
        this.empId = empId;
        this.empName = empName;
    }
}
