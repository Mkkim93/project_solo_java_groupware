package com.group.application.admin.service;

import com.group.application.admin.dto.RegisterDTO;
import com.group.application.hr.dto.EmployeeDTO;
import com.group.domain.hr.entity.Employee;
import com.group.domain.hr.repository.EmployeeRepository;
import com.group.exception.DuplicateEntityException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * # duplicateEmail : 사원 등록 시 이메일 중복 체크
 * # registration : 사원 정상 등록
 */
@Service
@RequiredArgsConstructor
public class AdminService {

    private final EmployeeRepository employeeRepository;

    public String duplicateEmail(String empEmail) {
        Boolean existsByEmpEmail = employeeRepository.existsByEmpEmail(empEmail);
        if (existsByEmpEmail) {
            throw new DuplicateEntityException("이미 등록된 이메일입니다. 다른 이메일 주소를 입력해 주세요");
        }
        return "사용 가능한 이메일입니다.";
    }

    public void registration(RegisterDTO registerDto) {
        Employee employee = registerDto.toEmployeeEntity(registerDto);
        employeeRepository.save(employee);
    }
}
