package com.group.domain.hr.repository;

import com.group.application.hr.dto.AttendanceDTO;
import com.group.application.hr.dto.DepartmentDTO;
import com.group.application.hr.dto.EmployeeDTO;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EmployeeRepositoryCustom {

    DepartmentDTO findByEmpDepartInfo(Integer id);

    AttendanceDTO findByOneEmpAttInfo(Integer id);

    List<AttendanceDTO> findByAllEmpAttInfo(Integer id, LocalDate attDate);
}
