package com.group.application.hr.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.group.application.hr.dto.QAttendanceDTO is a Querydsl Projection type for AttendanceDTO
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QAttendanceDTO extends ConstructorExpression<AttendanceDTO> {

    private static final long serialVersionUID = -1812306723L;

    public QAttendanceDTO(com.querydsl.core.types.Expression<Integer> id, com.querydsl.core.types.Expression<java.time.LocalDateTime> attOn, com.querydsl.core.types.Expression<java.time.LocalDateTime> attOff, com.querydsl.core.types.Expression<Long> attDuration, com.querydsl.core.types.Expression<Long> attOverDuration, com.querydsl.core.types.Expression<Long> attPerception, com.querydsl.core.types.Expression<Long> attLeave, com.querydsl.core.types.Expression<Long> attVacation, com.querydsl.core.types.Expression<java.time.LocalDate> attDate, com.querydsl.core.types.Expression<java.time.LocalDateTime> attCreate, com.querydsl.core.types.Expression<Integer> employee, com.querydsl.core.types.Expression<String> empName) {
        super(AttendanceDTO.class, new Class<?>[]{int.class, java.time.LocalDateTime.class, java.time.LocalDateTime.class, long.class, long.class, long.class, long.class, long.class, java.time.LocalDate.class, java.time.LocalDateTime.class, int.class, String.class}, id, attOn, attOff, attDuration, attOverDuration, attPerception, attLeave, attVacation, attDate, attCreate, employee, empName);
    }

}

