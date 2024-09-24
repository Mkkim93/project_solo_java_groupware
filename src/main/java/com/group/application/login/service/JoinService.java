package com.group.application.login.service;

import com.group.application.hr.dto.AttendanceDTO;
import com.group.application.hr.dto.EmployeeDTO;
import com.group.application.hr.service.AttendanceService;
import com.group.domain.hr.entity.Attendance;
import com.group.domain.hr.entity.Department;
import com.group.domain.hr.entity.Employee;
import com.group.domain.hr.repository.AttendanceRepository;
import com.group.domain.hr.repository.DepartmentRepository;
import com.group.domain.hr.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JoinService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final AttendanceRepository attendanceRepository;
    private final AttendanceService attendanceService;

    @Autowired
    public JoinService(EmployeeRepository employeeRepository,
                       BCryptPasswordEncoder bCryptPasswordEncoder,
                       DepartmentRepository departmentRepository,
                       AttendanceRepository attendanceRepository,
                       AttendanceService attendanceService) {
        this.employeeRepository = employeeRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.departmentRepository = departmentRepository;
        this.attendanceRepository = attendanceRepository;
        this.attendanceService = attendanceService;
    }

    /**
     * # 회원가입 : 사원 등록
     *
     * @param newEmpDtO
     */
    public void joinProcess(EmployeeDTO newEmpDtO) {
        Boolean isByEmpEmail = employeeRepository.existsByEmpEmail(newEmpDtO.getEmpEmail());
        // TODO
        if (isByEmpEmail) {
            return;
        }
        boolean isByAttId = attendanceRepository.existsById(newEmpDtO.getAttId());
        // TODO
        if (isByAttId) {
            return;
        }
        boolean isByDeptId = departmentRepository.existsById(newEmpDtO.getDeptId());
        // TODO
        if (!isByDeptId) {
            return;
        }
        attendanceService.saveAttendance();

        // TODO RuntimeException 처리
        Department deptId = departmentRepository.findById(newEmpDtO.getDeptId())
                .orElseThrow(() -> new RuntimeException());

        Attendance attId = attendanceRepository.findById(newEmpDtO.getAttId())
                .orElseThrow(() -> new RuntimeException());

        Employee employee = dtoConverterEmployee(newEmpDtO, deptId, attId);
        employeeRepository.save(employee);
    }

    // Dto -> Entity
    private Employee dtoConverterEmployee(EmployeeDTO newEmpDtO, Department deptId, Attendance attId) {
        Employee employee = Employee.builder()
                .empEmail(newEmpDtO.getEmpEmail())
                .empPass(bCryptPasswordEncoder.encode(newEmpDtO.getEmpPass()))
                .empRank(newEmpDtO.getEmpRank())
                .empName(newEmpDtO.getEmpName())
                .empRegNo(newEmpDtO.getEmpRegNo())
                .empNickname(newEmpDtO.getEmpNickName())
                .userTel(newEmpDtO.getUserTel())
                .userEmail(newEmpDtO.getUserEmail())
                .empIsAdmin(newEmpDtO.getEmpIsAdmin())
                .department(deptId)
                .attendance(attId)
                .empNo(newEmpDtO.getEmpNo())
                .build();
        return employee;
    }
}
