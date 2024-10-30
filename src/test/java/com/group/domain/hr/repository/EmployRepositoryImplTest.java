package com.group.domain.hr.repository;

import com.group.application.hr.dto.DepartmentDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EmployRepositoryImplTest {

    @Autowired
    private EmployRepositoryImpl employRepository;

    @Test
    void employeeDepartQuerydslTest() {
        Integer id = 1;
        DepartmentDTO empInfo = employRepository.findByEmpDepartInfo(id);
        System.out.println("empInfo.getDeptName() = " + empInfo.getDeptName());
        System.out.println("empInfo.getEmpName() = " + empInfo.getEmpName());
    }

}