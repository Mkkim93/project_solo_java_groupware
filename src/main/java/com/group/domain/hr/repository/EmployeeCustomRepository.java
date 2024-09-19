package com.group.domain.hr.repository;

import com.group.application.hr.dto.EmployeeDTO;
import com.group.domain.hr.entity.Employee;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.group.domain.hr.entity.QEmployee.employee;

@Repository
public class EmployeeCustomRepository {

    private final JPAQueryFactory query;

    public EmployeeCustomRepository(EntityManager em) {
        this.query = new JPAQueryFactory(em);
    }

    // TODO  왜 반환타입이 LONG 인가 찾아볼 것!
    public Long updateMyInfo(EmployeeDTO updateEmpDto, Integer empId) {
        return query.update(employee).set(employee.empName,
                        updateEmpDto.getEmpName())
                .set(employee.empNickname, updateEmpDto.getEmpNickName())
                .set(employee.empPass, updateEmpDto.getEmpPass())
                .set(employee.userEmail, updateEmpDto.getUserEmail())
                .where(employee.id.eq(empId)).execute();
    }
}
