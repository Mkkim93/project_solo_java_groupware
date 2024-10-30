package com.group.domain.hr.repository;

import com.group.application.hr.dto.AttendanceDTO;
import com.group.application.hr.dto.DepartmentDTO;
import com.group.application.hr.dto.EmployeeDTO;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepositoryCustom {

    DepartmentDTO findByEmpDepartInfo(Integer id);

    AttendanceDTO findByEmpAttInfo(Integer id);
}
