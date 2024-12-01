package com.group.application.login.service;

import com.group.application.hr.dto.EmployeeDTO;
import com.group.domain.hr.entity.Employee;
import com.group.domain.hr.repository.EmployeeRepository;
import com.group.exception.JoinException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class JoinService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final EmployeeRepository employeeRepository;

    /**
     * # 회원가입 : 사원 등록
     */
    public void save(EmployeeDTO dto) {
        Boolean exists = employeeRepository.existsByEmpEmail(dto.getEmpEmail());
        if (exists) {
            log.info("id 중복={}", false);
            throw new JoinException("이미 존재 하는 E-mail 입니다. 다시 입력해주세요");
        }
        log.info("id 중복 검증 ={}", true);
        employeeRepository.save(toEntity(dto));
    }

    private Employee toEntity(EmployeeDTO dto) {
        Employee e = Employee.builder()
                .empEmail(dto.getEmpEmail())
                .empPass(bCryptPasswordEncoder.encode(dto.getEmpPass()))
                .empName(dto.getEmpName())
                .empRegNo(dto.getEmpRegNo())
                .userEmail(dto.getUserEmail())
                .empNickname(dto.getEmpNickName())
                .build();
        return e;
    }
}
