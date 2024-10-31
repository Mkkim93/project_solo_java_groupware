package com.group.domain.hr.repository;

import com.group.domain.hr.entity.Attendance;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Integer> {

    Optional<Attendance> findByEmployeeIdAndAttOnBetween(Integer employeeId, LocalDateTime start, LocalDateTime end);

    @Modifying
    @Query("update Attendance a set a.attDuration = :attDuration where a.id = :id")
    void updateAttendanceByDuration(@Param("attDuration") Long attDuration, @Param("id") Integer id);


}
