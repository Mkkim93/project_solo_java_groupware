package com.group.application.department.service;

import com.group.application.hr.dto.DepartmentDTO;
import com.group.application.hr.service.DepartmentService;
import com.group.domain.hr.entity.Department;
import com.group.domain.hr.repository.DepartmentRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class DepartmentServiceTest {

    @Autowired
    DepartmentService departmentService;

    @Test
    @DisplayName("부서 정보 추가")
    public void saveDepartment() {
        DepartmentDTO departmentDTO = new DepartmentDTO("COM08", "해외영업팀");
        departmentService.saveDepartment(departmentDTO);
    }

    @Test
    @DisplayName("전체 부서 조회")
    public void findAllDepartment() {
        departmentService.findAllDepartment();
    }

    @Test
    @DisplayName("부서 정보 수정")
    public void updateDepartment() {
        DepartmentDTO departmentDTO = new DepartmentDTO(1);
        Integer deptId = departmentDTO.getId();
        departmentDTO.setDeptCode("D001");
        departmentDTO.setDeptName("기획팀");
        departmentService.updateDepart(deptId, departmentDTO);
    }

    @Test
    @DisplayName("부서 정보 삭제")
    public void deleteDepartment() {
        DepartmentDTO departmentDTO = new DepartmentDTO(8);
        departmentService.deleteDepartment(departmentDTO);
    }
}