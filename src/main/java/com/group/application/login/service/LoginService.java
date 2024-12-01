package com.group.application.login.service;

import com.group.application.hr.dto.EmployeeDTO;
import com.group.domain.hr.entity.Employee;
import com.group.domain.hr.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoginService {

    private final EmployeeRepository employeeRepository;
    private final BCryptPasswordEncoder encoder;

    public EmployeeDTO findByEmpInfo(EmployeeDTO dto) {
        Employee e = employeeRepository.findByEmpEmail(dto.getEmpEmail());
        if (e == null) {
            throw new BadCredentialsException("Invalid credentials");
        }
        boolean matches = encoder.matches(dto.getEmpPass(), e.getEmpPass());
        if (!matches) {
            throw new BadCredentialsException("Invalid credentials");
        }
        log.info("Login Pass");
        return new EmployeeDTO(e);
    }
}
