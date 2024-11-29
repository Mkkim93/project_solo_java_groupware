package com.group.application.login.service;

import com.group.application.hr.dto.EmployeeDTO;
import com.group.domain.hr.entity.Employee;
import com.group.domain.hr.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final EmployeeRepository employeeRepository;

    public EmployeeDTO findByEmpInfo(String empEmail, String empPass) {
        Employee entity = employeeRepository.findByEmpEmailAndPass(empEmail, empPass);
        EmployeeDTO dto = new EmployeeDTO();
        return dto.toDtoByIdEmail(entity);
    }
}
