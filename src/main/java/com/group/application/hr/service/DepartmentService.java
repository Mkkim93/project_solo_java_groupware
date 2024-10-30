package com.group.application.hr.service;

import com.group.application.hr.dto.DepartmentDTO;
import com.group.domain.hr.entity.Department;
import com.group.domain.hr.entity.QEmployee;
import com.group.domain.hr.repository.DepartmentRepository;
import com.group.domain.hr.repository.EmployRepositoryImpl;
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

    // 부서 조회
    public DepartmentDTO findByIdDepartment(DepartmentDTO departmentDTO) {
        return null;
    }
}

