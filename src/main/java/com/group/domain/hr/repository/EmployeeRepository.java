package com.group.domain.hr.repository;

import com.group.application.hr.dto.EmployeeDTO;
import com.group.domain.hr.entity.Employee;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;


@Transactional
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    Employee findByEmpEmail(String empEmail);

    Boolean existsByEmpEmail(String empEmail);

    Employee findByEmpUUID(String empUUID);

    @Query("select e from Employee e where e.empUUID = :empUUID")
    Employee findByEmployeeEntity(@Param("empUUID") String empUUID);

    @Query("select e from Employee e where e.empUUID = :empUUID")
    EmployeeDTO findByEmployee(@Param("empUUID") String empUUID);

}
