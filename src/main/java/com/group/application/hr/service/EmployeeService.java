package com.group.application.hr.service;

import com.group.application.hr.dto.AttendanceDTO;
import com.group.application.hr.dto.EmployeeDTO;
import com.group.domain.hr.entity.Attendance;
import com.group.domain.hr.entity.Department;
import com.group.domain.hr.entity.Employee;
import com.group.domain.hr.repository.EmployeeCustomRepository;
import com.group.domain.hr.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    @Autowired
    private final EmployeeRepository employeeRepository;

    @Autowired
    private final AttendanceService attendanceService;

    @Autowired
    private final EmployeeCustomRepository employeeCustomRepository;

    // 회원 가입
    public String saveEmployee(EmployeeDTO newEmpDto) {
        Employee newEmpInfo = createEmployee(newEmpDto);
        employeeRepository.save(newEmpInfo);
        return newEmpInfo.getEmpName();
    }

    // 모든 사원 조회
    public List<Employee> findAllEmployee() {
        List<Employee> employees = employeeRepository.findAll();
        return employees;
    }

    // 나의 정보 조회
    public List<Employee> findByEmployee(Integer empId) {
        return employeeRepository.findById(empId).stream().toList();
    }

    // 나의 근태 조회
    public List<Object[]> findByMyAttInfo(String empName) {
        return employeeRepository.findByAttendance(empName);
    }

    // 나의 정보 수정
    public void updateMyInfo() {

    }

    // 회원 가입 메서드
    public Employee createEmployee(EmployeeDTO newEmpDtO) {
        Employee employee = new Employee();

        employee.setEmpPass(newEmpDtO.getEmpPass());
        employee.setEmpName(newEmpDtO.getEmpName());
        employee.setEmpRank(newEmpDtO.getEmpRank());
        employee.setEmpRegNo(newEmpDtO.getEmpRegNo());
        employee.setEmpNickname(newEmpDtO.getEmpNickName());
        employee.setUserTel(newEmpDtO.getUserTel());
        employee.setUserEmail(newEmpDtO.getUserEmail());
        employee.setEmpEmail(newEmpDtO.getEmpEmail());
        employee.setEmpMileage(newEmpDtO.getEmpMileage());
        employee.setEmpJoinYN(newEmpDtO.getEmpJoinYn());
        employee.setEmpIsAdmin(newEmpDtO.getEmpIsAdmin());
        employee.setEmpNo(newEmpDtO.getEmpNo());

        Department department = new Department();
        Attendance attendance = new Attendance();
        AttendanceDTO attendanceDTO = new AttendanceDTO();

        department.setId(newEmpDtO.getDeptId());

        Attendance newAttendance = attendanceService.saveAttendance(attendanceDTO);

        attendance.setId(newAttendance.getId());

        // 부서정보, 근태정보 추가
        employee.setDepartment(department);
        employee.setAttendance(attendance);

        return employee;
    }

}