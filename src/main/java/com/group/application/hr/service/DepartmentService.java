package com.group.application.hr.service;

import com.group.application.hr.dto.DepartmentDTO;
import com.group.domain.hr.entity.Department;
import com.group.domain.hr.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class DepartmentService {

    @Autowired
    private final DepartmentRepository departmentrepository;

    /**
     * 부서 업데이트
     * @param departCode 기본키를 입력
     * @param updateParam 입력된 기본키의 부서코드, 부서명 변경
     */
    public void updateDepart(Integer departCode, DepartmentDTO updateParam) {
        Department updateDepart = departmentrepository.findById(departCode).orElseThrow();
        updateDepart.setDeptCode(updateParam.getDeptCode());
        updateDepart.setDeptName(updateParam.getDeptName());
        departValidation(departCode, updateDepart);
        departmentrepository.save(updateDepart);
    }

    // TODO validation : department Code 없을 때 예외
    private static void departValidation(Integer departCode, Department updateDepart) {
        if (departCode != updateDepart.getId()) {
            log.info("부서 코드가 존재하지 않습니다.");
        }
    }

    /**
     * 부서 삭제
     * @param deleteParam
     */
    public void deleteDepartment(DepartmentDTO deleteParam) {
        Integer deptId = deleteParam.getId();
        departmentrepository.deleteById(deptId);
    }

    /**
     * 부서 등록
     * @param saveParam
     */
    public void saveDepartment(DepartmentDTO saveParam) {
        Department department = new Department();
        department.setDeptCode(saveParam.getDeptCode());
        department.setDeptName(saveParam.getDeptName());
        departmentrepository.save(department);
    }

    /**
     * 부서 조회 (all)
     */
    public void findAllDepartment() {
        List<Department> departmentList = departmentrepository.findAll();
        departmentList.stream().forEach(System.out::println);
    }
}

