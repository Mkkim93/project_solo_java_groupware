package com.group.domain.hr.repository;


import com.group.application.hr.dto.*;
import com.group.domain.hr.entity.Employee;
import com.group.domain.hr.entity.QAttendance;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
    public AttendanceDTO findByEmpAttInfo(Integer id) {

        return jpaQueryFactory.select(new QAttendanceDTO(
                attendance.id,
                attendance.attOn,
                attendance.attOff,
                attendance.attPerception,
                attendance.attLeave,
                attendance.attVacation,
                attendance.attDate,
                attendance.attCreate,
                employee.empName,
                attendance.employee.id))
                .from(attendance)
                .join(attendance.employee, employee)
                .where(attendance.employee.id.eq(id))
                .fetchOne();
    }
}
