package com.group.application.hr.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.group.application.hr.dto.QDepartmentDTO is a Querydsl Projection type for DepartmentDTO
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QDepartmentDTO extends ConstructorExpression<DepartmentDTO> {

    private static final long serialVersionUID = 43499476L;

    public QDepartmentDTO(com.querydsl.core.types.Expression<Integer> id, com.querydsl.core.types.Expression<String> deptCode, com.querydsl.core.types.Expression<String> deptName, com.querydsl.core.types.Expression<Integer> empId, com.querydsl.core.types.Expression<String> empName) {
        super(DepartmentDTO.class, new Class<?>[]{int.class, String.class, String.class, int.class, String.class}, id, deptCode, deptName, empId, empName);
    }

}

