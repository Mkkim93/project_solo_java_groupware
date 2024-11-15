package com.group.application.hr.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.group.application.hr.dto.QEmployeeDTO is a Querydsl Projection type for EmployeeDTO
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QEmployeeDTO extends ConstructorExpression<EmployeeDTO> {

    private static final long serialVersionUID = -557282120L;

    public QEmployeeDTO(com.querydsl.core.types.Expression<Integer> id, com.querydsl.core.types.Expression<String> empEmail, com.querydsl.core.types.Expression<String> empPass, com.querydsl.core.types.Expression<String> empName, com.querydsl.core.types.Expression<String> empRegNo, com.querydsl.core.types.Expression<String> userEmail, com.querydsl.core.types.Expression<String> empNickName, com.querydsl.core.types.Expression<String> empRank, com.querydsl.core.types.Expression<String> userTel, com.querydsl.core.types.Expression<String> empNo, com.querydsl.core.types.Expression<Integer> empMileage, com.querydsl.core.types.Expression<String> empImg, com.querydsl.core.types.Expression<String> empTel, com.querydsl.core.types.Expression<java.time.LocalDateTime> empJoinDate, com.querydsl.core.types.Expression<Integer> deptId, com.querydsl.core.types.Expression<Integer> attId) {
        super(EmployeeDTO.class, new Class<?>[]{int.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, int.class, String.class, String.class, java.time.LocalDateTime.class, int.class, int.class}, id, empEmail, empPass, empName, empRegNo, userEmail, empNickName, empRank, userTel, empNo, empMileage, empImg, empTel, empJoinDate, deptId, attId);
    }

}

