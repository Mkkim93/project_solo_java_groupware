package com.group.domain.hr.repository;


import com.group.application.hr.dto.*;
import com.group.domain.hr.entity.Employee;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static com.group.domain.hr.entity.QAttendance.*;
import static com.group.domain.hr.entity.QDepartment.*;
import static com.group.domain.hr.entity.QEmployee.*;

@Repository
@Transactional
public class EmployeeRepositoryImpl extends QuerydslRepositorySupport implements EmployeeRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    public EmployeeRepositoryImpl(EntityManager entityManager) {
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

    // TODAY Attendance select All
    @Override
    public AttendanceDTO findByOneEmpAttInfo(Integer id) {
        LocalDate today = LocalDate.now();
        return jpaQueryFactory.select(new QAttendanceDTO(
                        attendance.id, attendance.attOn, attendance.attOff,
                        attendance.attDuration, attendance.attOverDuration, attendance.attPerception,
                        attendance.attLeave, attendance.attVacation,
                        attendance.attDate, attendance.attCreate,
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
    public Page<AttendanceDTO> findByAllEmpAttInfo(Integer id, LocalDate attDate, PageRequest pageRequest) {
        int searchByMonth = attDate.getMonthValue(); // 월단위 조회 변수
        List<AttendanceDTO> results = jpaQueryFactory.select(new QAttendanceDTO(
                        attendance.id, attendance.attOn, attendance.attOff, attendance.attDuration,
                        attendance.attOverDuration.count(),
                        attendance.attPerception.sum(), attendance.attLeave.sum(), attendance.attVacation.sum(),
                        attendance.attDate, attendance.attCreate, attendance.employee.id,
                        employee.empName))
                .from(attendance)
                .join(attendance.employee, employee)
                .groupBy(attendance.id)
                .where(
                        attendance.employee.id.eq(id),
                        Expressions.numberTemplate(Integer.class, "MONTH({0})", attendance.attDate).eq(searchByMonth)
                )
                .offset(pageRequest.getOffset())
                .limit(pageRequest.getPageSize())
                .fetch();

        JPAQuery<Long> count = jpaQueryFactory
                .select(attendance.count())
                .from(attendance)
                .join(attendance.employee, employee);

        return PageableExecutionUtils.getPage(results, pageRequest, () -> count.fetchCount());
    }
}
