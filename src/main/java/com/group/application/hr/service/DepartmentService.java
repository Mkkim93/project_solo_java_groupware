package com.group.application.hr.service;

import com.group.application.hr.dto.DepartmentDTO;
import com.group.domain.hr.entity.Department;
import com.group.domain.hr.repository.DepartmentRepository;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.group.domain.hr.entity.QDepartment.*;

@Service
@Transactional
public class DepartmentService {

    private final DepartmentRepository departmentrepository;

    @Autowired
    private EntityManager entityManager;

    private JPAQueryFactory jpaQueryFactory;

    public DepartmentService(DepartmentRepository departmentrepository, EntityManager entityManager) {
        this.departmentrepository = departmentrepository;
        jpaQueryFactory = new JPAQueryFactory(entityManager);
    }

    // 부서 생성
    public void save(DepartmentDTO departmentDTO) {

        Department department = getEntityForSave(departmentDTO);
        departmentrepository.save(department);
    }

    // DTO -> Entity
    private Department getEntityForSave(DepartmentDTO departmentDTO) {

        Department department = Department.builder()
                .deptCode(departmentDTO.getDeptCode())
                .deptName(departmentDTO.getDeptName())
                .deptIsDeleted("Y")
                .build();
        return department;
    }

    // 전체 부서 조회
    public List<DepartmentDTO> findAll() {

        return jpaQueryFactory
                .select(Projections.fields(DepartmentDTO.class,
                        department.id,
                        department.deptCode,
                        department.deptName))
                .from(department)
                .fetch();
    }

    // 부서 정보 수정
    public Department update(DepartmentDTO departmentDTO) {
        Department department = departmentrepository
                .findById(departmentDTO.getId())
                .orElseThrow(() -> new EntityNotFoundException(""));

        department.setDeptName(departmentDTO.getDeptName());
        department.setDeptCode(departmentDTO.getDeptCode());

        return departmentrepository.save(department);
    }

    // 부서 삭제 isdeleted : (N -> Y)
    public void delete(DepartmentDTO departmentDTO) {

        Department department = departmentrepository
                .findById(departmentDTO.getId())
                .orElseThrow(() -> new EntityNotFoundException("notFountEntity " + departmentDTO.getId()));

        department.setDeptIsDeleted("Y");

        departmentrepository.save(department);
    }

    // 삭제된 부서 활성화 isdeleted : (Y -> N)
    public void enable(DepartmentDTO departmentDTO) {
        Department department = departmentrepository
                .findById(departmentDTO.getId())
                .orElseThrow(() -> new EntityNotFoundException("notFountEntity " + departmentDTO.getId()));

        department.setDeptIsDeleted("N");

        departmentrepository.save(department);
    }
}

