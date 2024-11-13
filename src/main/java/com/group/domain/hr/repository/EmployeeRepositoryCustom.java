package com.group.domain.hr.repository;

import com.group.application.hr.dto.AttendanceDTO;
import com.group.application.hr.dto.DepartmentDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface EmployeeRepositoryCustom {

    DepartmentDTO findByEmpDepartInfo(Integer id);

    AttendanceDTO findByOneEmpAttInfo(Integer id);

    Page<AttendanceDTO> findByAllEmpAttInfo(Integer id, LocalDate attDate, PageRequest pageRequest);
}
