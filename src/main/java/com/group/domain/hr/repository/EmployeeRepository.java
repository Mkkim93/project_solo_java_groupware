package com.group.domain.hr.repository;

import com.group.domain.hr.entity.Employee;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    // 모든 정보 조회
    Optional<Employee> findById(Integer empId);

    // 나의 근태 현황
    @Query("select e.empName, a.attOn, a.attOff, a.attPerception, a.attLeave, " +
            " a.attVacation, a.attDate from Employee e join Attendance a " +
            " on e.attendance.id = a.id where e.empName = :empName")
    List<Object[]> findByAttendance(@Param("empName") String empName);
}
