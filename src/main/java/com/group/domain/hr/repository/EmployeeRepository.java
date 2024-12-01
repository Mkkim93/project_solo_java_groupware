package com.group.domain.hr.repository;

import com.group.domain.hr.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    Boolean existsByEmpEmail(String empEmail);

    Employee findByEmpEmail(String empEmail);
}
