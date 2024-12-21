package com.group.application.login.service;

import com.group.application.cookie.CookieUtil;
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
    private final CookieUtil cookieUtil;

    public EmployeeDTO login(EmployeeDTO dto) {
        Employee e = employeeRepository.findByEmpEmail(dto.getEmpEmail());
        if (e == null) {
            throw new BadCredentialsException("Invalid credentials");
        }
        boolean matches = encoder.matches(dto.getEmpPass(), e.getEmpPass());
        if (!matches) {
            throw new BadCredentialsException("Invalid credentials");
        }

        log.info("Login Pass");
        log.info("empPass={}", e.getEmpPass());
        cookieUtil.saveCookie(dto.toDto(e));

        return dto.toDto(e);
    }
}
