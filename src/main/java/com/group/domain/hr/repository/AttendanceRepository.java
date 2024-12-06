package com.group.domain.hr.repository;

import com.group.application.hr.dto.AttendanceDTO;
import com.group.domain.hr.entity.Attendance;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface AttendanceRepository extends JpaRepository<Attendance, Integer> {

    Optional<Attendance> findByEmployeeIdAndAttOnBetween(Integer employeeId, LocalDateTime start, LocalDateTime end);

    // 출근 시간 ~ 퇴근 시간의 정규 근무시간 8시간은 기존의 근무 시간을 포함한 시간이 업데이트 된다.
    // 기존의 근무 시간이란 정규 근무 시간중에 연장 근무를 신청하면 먼저 근무 시간이 누적되기 때문에
    // ex) 금일 15:00 에 연장근무 2시간을 신청을 하고 부서장의 승인이 완료되면 정규 근무시간이 추가되기 이전에 연장근무시간이 먼저 저장될 수 있다.
    // 상황에 따라 먼저 저장된 연장근무시간 + 정규근무시간이 총 attDuration 이 될 수 있다.
    @Modifying
    @Query("update Attendance a set a.attDuration = :attDuration where a.id = :id")
    void updateAttendanceByDuration(@Param("attDuration") Long attDuration, @Param("id") Integer id);

    @Modifying
    @Query("update Attendance a set a.attPerception = a.attPerception + 1 where a.id = :id")
    void updatePerceptionCount(@Param("id") Integer id);

    @Query("select new com.group.application.hr.dto.AttendanceDTO(sum(a.attDuration), sum(a.attOverDuration), sum(a.attPerception), a.employee.id) " +
            "from Attendance a where a.employee.id = :id and " +
            "function('YEAR', a.attDate) = :year " +
            "and function('MONTH', a.attDate) = :month " +
            "group by a.employee.id")
    AttendanceDTO getAttendanceByOfMonthDuration(@Param("id") Integer id, @Param("year") Integer year, @Param("month") Integer month);

    @Query("select new com.group.application.hr.dto.AttendanceDTO(sum(a.attDuration), sum(a.attOverDuration), sum(a.attPerception), a.employee.id) " +
            "from Attendance a where a.employee.id = :id and a.attDate between :startDay and :endDay " +
            "group by a.employee.id")
    AttendanceDTO getAttendanceByOfWeekDuration(@Param("id") Integer id, @Param("startDay") LocalDate startDay, @Param("endDay") LocalDate endDay);

    @Query("select new com.group.application.hr.dto.AttendanceDTO(sum(a.attPerception), sum(a.attLeave), sum(a.attVacation), count(a.attOverDuration))" +
            "from Attendance a where a.employee.id = :id and a.attDate between :startOfWeek and :endOfWeek")
    AttendanceDTO rangeOfAttendanceByWeek(@Param("id") Integer id,
                                                  @Param("startOfWeek") LocalDate startOfWeek,
                                                  @Param("endOfWeek") LocalDate endOfWeek);
}
