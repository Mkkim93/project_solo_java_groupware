package com.group.domain.hr.repository;

import com.group.domain.hr.entity.Employee;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    Boolean existsByEmpEmail(String empEmail);

    @Query("select e from Employee e where e.empEmail = :empEmail")
    Employee findByEmpEmail(@Param("empEmail") String empEmail);

}
