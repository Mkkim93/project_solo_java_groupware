package com.group.domain.hr.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class EmployeeCustomRepository {

    private final JPAQueryFactory query;

    public EmployeeCustomRepository(EntityManager em) {
        this.query = new JPAQueryFactory(em);
    }
}
