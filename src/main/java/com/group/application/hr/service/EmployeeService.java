package com.group.application.hr.service;

import com.group.application.hr.dto.AttendanceDTO;
import com.group.application.hr.dto.EmployeeDTO;
import com.group.domain.hr.entity.Attendance;
import com.group.domain.hr.entity.Department;
import com.group.domain.hr.entity.Employee;
import com.group.domain.hr.repository.EmployeeCustomRepository;
import com.group.domain.hr.repository.EmployeeRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    /*public EmployeeDTO saveEmployee(EmployeeDTO newEmpDto) {
        // Employee EmpJoin = createEmployee(newEmpDto);
        // employeeRepository.save(EmpJoin);
        return null;
    }*/

    // 모든 사원 조회
    public List<Employee> findAllEmployee() {
        List<Employee> employees = employeeRepository.findAll();
        return employees;
    }

    // 나의 정보 조회
    public List<Employee> findByEmployee(Integer empId) {
        employeeRepository.existsById(empId);
        return employeeRepository.findById(empId).stream().toList();
    }

    // 나의 근태 조회
    public List<Object[]> findByMyAttInfo(String empName) {
        return employeeRepository.findByAttendance(empName);
    }

    // 나의 정보 수정
    public void updateMyInfo(Integer empId, EmployeeDTO updateParam) {
        updateParam.getUserEmail();
        updateParam.getEmpNickName();
        updateParam.getEmpName();
        updateParam.getEmpPass();
        employeeCustomRepository.updateMyInfo(updateParam, empId);
    }

    public Employee findByEmpEmail(EmployeeDTO employeeDTO) {

        return employeeRepository.findByEmpEmail(employeeDTO.getEmpEmail());
    }
}