package com.group.domain.hr.repository;

import com.group.domain.hr.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Integer> {

    Optional<Attendance> findByEmployeeIdAndAttOnBetween(Integer employeeId, LocalDateTime start, LocalDateTime end);
}
