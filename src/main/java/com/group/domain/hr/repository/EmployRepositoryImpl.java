package com.group.domain.hr.repository;


import com.group.application.hr.dto.*;
import com.group.domain.hr.entity.Employee;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.DateTimePath;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Date;
import java.util.List;

import static com.group.domain.hr.entity.QAttendance.*;
import static com.group.domain.hr.entity.QDepartment.*;
import static com.group.domain.hr.entity.QEmployee.*;

@Repository
@Transactional
public class EmployRepositoryImpl extends QuerydslRepositorySupport implements EmployeeRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    public EmployRepositoryImpl(EntityManager entityManager) {
        super(Employee.class);
        jpaQueryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public DepartmentDTO findByEmpDepartInfo(Integer id) {
        return jpaQueryFactory.select(new QDepartmentDTO(department.id,
                        department.deptCode,
                        department.deptName,
                        employee.id, employee.empName))
                .from(department)
                .join(department.employees, employee)
                .where(employee.id.eq(id))
                .fetchOne();
    }

    @Override
    public AttendanceDTO findByOneEmpAttInfo(Integer id) {
        LocalDate today = LocalDate.now();

        return jpaQueryFactory.select(new QAttendanceDTO(
                        attendance.id,
                        attendance.attOn,
                        attendance.attOff,
                        attendance.attDuration,
                        attendance.attPerception,
                        attendance.attLeave,
                        attendance.attVacation,
                        attendance.attDate,
                        attendance.attCreate,
                        attendance.employee.id,
                        employee.empName))
                .from(attendance)
                .join(attendance.employee, employee)
                .where(
                        attendance.employee.id.eq(id),
                        attendance.attDate.eq(today) // 오늘 날짜와 일치하는 데이터 조회
                )
                .fetchOne();
    }

    @Override
    public List<AttendanceDTO> findByAllEmpAttInfo(Integer id, LocalDate attDate) {
        int searchByMonth = attDate.getMonthValue(); // 입력한 월을 정수로 변경해서 조건과 비교
        return jpaQueryFactory.select(new QAttendanceDTO(
                        attendance.id,
                        attendance.attOn,
                        attendance.attOff,
                        attendance.attDuration,
                        attendance.attPerception.sum(),
                        attendance.attLeave.sum(),
                        attendance.attVacation.sum(),
                        attendance.attDate,
                        attendance.attCreate,
                        attendance.employee.id,
                        employee.empName))
                .from(attendance)
                .join(attendance.employee, employee)
                .groupBy(attendance.id) // Optional depending on your needs
                .where(
                        attendance.employee.id.eq(id),
                        Expressions.numberTemplate(Integer.class, "MONTH({0})", attendance.attDate).eq(searchByMonth)
                )
                .fetch();
    }
}
