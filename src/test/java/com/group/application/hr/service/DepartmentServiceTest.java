package com.group.application.hr.service;

import com.group.application.hr.dto.DepartmentDTO;
import com.group.domain.hr.entity.Department;
import com.group.domain.hr.repository.DepartmentRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@Slf4j
@SpringBootTest
@RequiredArgsConstructor
class DepartmentServiceTest {

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Test
    @DisplayName("새로운 부서 생성")
    public void saveDepartment() {
        DepartmentDTO departDto = new DepartmentDTO("D012", "개발10팀");
        departmentService.save(departDto);
    }

    @Test
    @DisplayName("모든 부서 조회")
    public void findAllDepartment() {
        List<DepartmentDTO> departmentDTOList = departmentService.findAll();
        for (DepartmentDTO dto : departmentDTOList) {
            System.out.println("dto = " + dto);
        }
    }

    @Test
    @DisplayName("부서 정보 업데이트")
    public void updateDepartment() {
        DepartmentDTO departDto = new DepartmentDTO();

        departDto.setId(8);
        departDto.setDeptCode("D008");
        departDto.setDeptName("개발4팀");

        Department updated = departmentService.update(departDto);
        assertThat(updated).extracting("deptName").isNotEqualTo(departDto);
    }

    @Test
    @DisplayName("부서 삭제 (Y/N)")
    public void deleteDepartment() {
        DepartmentDTO departDto = new DepartmentDTO();
        departDto.setId(11);
        departmentService.delete(departDto);
    }
}