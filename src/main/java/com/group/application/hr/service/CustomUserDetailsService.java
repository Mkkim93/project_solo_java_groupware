package com.group.application.hr.service;

import com.group.application.login.dto.CustomUserDetails;
import com.group.domain.hr.entity.Employee;
import com.group.domain.hr.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final EmployeeRepository employeeRepository;
    // DB 접근 후 특정 Employee 데이터 return
    @Override
    public UserDetails loadUserByUsername(String empEmail) throws UsernameNotFoundException {

        Employee employee = employeeRepository.findByEmpEmail(empEmail);

        if (employee != null) {
            return new CustomUserDetails(employee);
        }
        return null;
    }
}
