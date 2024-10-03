package com.group.application.login.service;

import com.group.application.hr.dto.EmployeeDTO;
import com.group.application.hr.service.AttendanceService;
import com.group.application.hr.service.DepartmentService;
import com.group.domain.hr.entity.Employee;
import com.group.domain.hr.repository.AttendanceRepository;
import com.group.domain.hr.repository.DepartmentRepository;
import com.group.domain.hr.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class JoinService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final AttendanceRepository attendanceRepository;
    private final AttendanceService attendanceService;
    private final DepartmentService departmentService;

    @Autowired
    public JoinService(EmployeeRepository employeeRepository,
                       BCryptPasswordEncoder bCryptPasswordEncoder,
                       DepartmentRepository departmentRepository,
                       AttendanceRepository attendanceRepository,
                       AttendanceService attendanceService,
                       DepartmentService departmentService) {
        this.employeeRepository = employeeRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.departmentRepository = departmentRepository;
        this.attendanceRepository = attendanceRepository;
        this.attendanceService = attendanceService;
        this.departmentService = departmentService;
    }

    /**
     * # 회원가입 : 사원 등록
     */
    public EmployeeDTO joinProcess(EmployeeDTO newEmpDto) {
        Boolean isByEmpEmail = employeeRepository.existsByEmpEmail(newEmpDto.getEmpEmail());
        if (isByEmpEmail) {
            log.info("id 중복 true or false ={}", true);
            return null;
        }
        Employee employee = getEntityForJoin(newEmpDto);
        Employee saveEmployee = employeeRepository.save(employee);
        EmployeeDTO savedEmpDto = getDtoForJoin(saveEmployee);
        return savedEmpDto;
    }

    private EmployeeDTO getDtoForJoin(Employee saveEmployee) {

        EmployeeDTO employeeDTO = EmployeeDTO.builder()
                .empEmail(saveEmployee.getEmpEmail())
                .empPass(saveEmployee.getEmpPass())
                .empName(saveEmployee.getEmpName())
                .empRegNo(saveEmployee.getEmpRegNo())
                .userEmail(saveEmployee.getUserEmail())
                .empNickName(saveEmployee.getEmpNickname())
                .build();
        return employeeDTO;
    }

    private Employee getEntityForJoin(EmployeeDTO newEmpDto) {
        Employee empEntity = Employee.builder()
                .empEmail(newEmpDto.getEmpEmail())
                .empPass(bCryptPasswordEncoder.encode(newEmpDto.getEmpPass()))
                .empName(newEmpDto.getEmpName())
                .empRegNo(newEmpDto.getEmpRegNo())
                .userEmail(newEmpDto.getUserEmail())
                .empNickname(newEmpDto.getEmpNickName())
                .build();
        return empEntity;
    }
}
