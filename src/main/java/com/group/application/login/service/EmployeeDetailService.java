package com.group.application.login.service;

import com.group.application.login.dto.CustomUserDetails;
import com.group.domain.hr.entity.Employee;
import com.group.domain.hr.repository.EmployeeRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeDetailService implements UserDetailsService {

    private final EmployeeRepository employeeRepository;

    public EmployeeDetailService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String empEmail) throws UsernameNotFoundException {

        Employee byEmpEmail = employeeRepository.findByEmpEmail(empEmail);

        if (byEmpEmail != null) {
            return new CustomUserDetails(byEmpEmail);
        }
        throw new UsernameNotFoundException("not found" + empEmail + " by email");
    }
}
