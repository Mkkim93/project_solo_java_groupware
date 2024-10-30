package com.group.application.hr.service;

import com.group.application.hr.dto.AttendanceDTO;
import com.group.application.hr.dto.DepartmentDTO;
import com.group.application.hr.dto.EmployeeDTO;
import com.group.domain.hr.entity.Employee;
import com.group.domain.hr.repository.EmployRepositoryImpl;
import com.group.domain.hr.repository.EmployeeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {


    private final EmployeeRepository employeeRepository;
    private final EmployRepositoryImpl employRepositoryImpl;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository,
                           EmployRepositoryImpl employRepositoryImpl) {
        this.employeeRepository = employeeRepository;
        this.employRepositoryImpl = employRepositoryImpl;
    }

    public EmployeeDTO findByAll(EmployeeDTO employeeDTO) {
        Employee employee = employeeRepository.findById(employeeDTO.getId()).orElseThrow(() -> new EntityNotFoundException("no id"));
        employeeDTO.setEmployeeDTO(employee);
        return employeeDTO;
    }

    public void updateProfile(EmployeeDTO employeeDTO) {
        Employee employee = employeeRepository.findById(employeeDTO.getId()).orElseThrow(() -> new EntityNotFoundException("no id"));
        employee.setUserEmail(employeeDTO.getUserEmail());
        employee.setUserTel(employeeDTO.getUserTel());
        employee.setEmpTel(employeeDTO.getEmpTel());
        employeeRepository.save(employee);
    }

    public EmployeeDTO findById(Integer id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("no id"));
        EmployeeDTO employeeDTO = new EmployeeDTO();
        return employeeDTO.setEmployeeDTO(employee);
    }

    public DepartmentDTO findByIdDepartInfo(EmployeeDTO employeeDTO) {
        return employRepositoryImpl.findByEmpDepartInfo(employeeDTO.getId());
    }

    public AttendanceDTO findByIdAttInfo(EmployeeDTO employeeDTO) {
        return employRepositoryImpl.findByEmpAttInfo(employeeDTO.getId());
    }
}