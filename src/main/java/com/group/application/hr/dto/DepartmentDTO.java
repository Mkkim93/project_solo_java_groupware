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

    public DepartmentDTO(Integer id) {
        this.id = id;
    }

    @QueryProjection
    public DepartmentDTO(String deptCode, String deptName) {
        this.deptCode = deptCode;
        this.deptName = deptName;
        this.deptIsDeleted = getDeptIsDeleted();
    }

    public DepartmentDTO(Integer id, String deptCode, String deptName) {
        this.id = id;
        this.deptCode = deptCode;
        this.deptName = deptName;
    }
}
