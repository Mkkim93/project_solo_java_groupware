package com.group.application.hr.service;

import com.group.application.login.dto.CustomUserDetails;
import com.group.domain.hr.entity.Employee;
import com.group.domain.hr.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Slf4j
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final EmployeeRepository employeeRepository;

    public CustomUserDetailsService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    // DB 접근 후 특정 Employee 데이터 return
    @Override
    public UserDetails loadUserByUsername(String empEmail) throws UsernameNotFoundException {
        Employee employee = employeeRepository.findByEmpEmail(empEmail);
        log.info("Employee fetched from DB: {}", employee.getRoleType());

        if (employee.getEmpEmail() != null) {
            return new CustomUserDetails(employee);
        }
        log.info("empEmail null");
        return null;
    }
}
