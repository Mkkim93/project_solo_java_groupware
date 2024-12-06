package com.group.application.hr.service;

import com.group.application.hr.dto.DepartmentDTO;
import com.group.application.hr.dto.EmployeeDTO;
import com.group.domain.hr.entity.Employee;
import com.group.domain.hr.repository.EmployeeRepositoryImpl;
import com.group.domain.hr.repository.EmployeeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * TODO EntityNotFoundException 처리 (컨트롤러 어드바이저에서 진행 ?)
 */
@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeRepositoryImpl employeeRepositoryImpl;

    public EmployeeDTO findByAll(EmployeeDTO dto) {
        EmployeeDTO findDto = employeeRepository.findByEmployee(dto.getEmpUUID());
        return findDto;
    }

    public void updateProfile(EmployeeDTO dto) {
        Employee entity = employeeRepository.findByEmployeeEntity(dto.getEmpUUID());
        entity.setUserEmail(dto.getUserEmail());
        entity.setUserTel(dto.getUserTel());
        entity.setEmpTel(dto.getEmpTel());
        employeeRepository.save(entity);
    }

    public EmployeeDTO findById(Integer id) {
        Employee entity = employeeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("no id"));
        EmployeeDTO dto = new EmployeeDTO();
        return dto.toDto(entity);
    }

    public DepartmentDTO findByIdDepartInfo(EmployeeDTO dto) {
        EmployeeDTO findDto = employeeRepository.findByEmployee(dto.getEmpUUID());
        return employeeRepositoryImpl.findByEmpDepartInfo(findDto.getId());
    }

    // TEST
    public Employee findByEmpEmail(String empEmail) {
        return employeeRepository.findByEmpEmail(empEmail);
    }

    public EmployeeDTO findByEmployee(String empUUID) {
        return employeeRepository.findByEmployee(empUUID);
    }

    public Integer findByIDFromUUID(String empUUID) {
        return employeeRepository.findByIdFromUUID(empUUID);
    }
}