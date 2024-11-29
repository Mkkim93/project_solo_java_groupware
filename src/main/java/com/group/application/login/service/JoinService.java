package com.group.application.login.service;

import com.group.application.hr.dto.EmployeeDTO;
import com.group.domain.hr.entity.Employee;
import com.group.domain.hr.repository.EmployeeRepository;
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
    public EmployeeDTO save(EmployeeDTO dto) {
        Boolean existsByEmpEmail = employeeRepository.existsByEmpEmail(dto.getEmpEmail());
        if (existsByEmpEmail) {
            log.info("id 중복 검증 ={}", true);
            return null;
        }
        Employee savedEntity = employeeRepository.save(toEntity(dto));
        return toDto(savedEntity);
    }

    private EmployeeDTO toDto(Employee e) {

        EmployeeDTO dto = EmployeeDTO.builder()
                .empEmail(e.getEmpEmail())
                .empPass(e.getEmpPass())
                .empName(e.getEmpName())
                .empRegNo(e.getEmpRegNo())
                .userEmail(e.getUserEmail())
                .empNickName(e.getEmpNickname())
                .build();
        return dto;
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
