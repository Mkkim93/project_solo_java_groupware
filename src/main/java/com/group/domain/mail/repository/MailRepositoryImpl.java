package com.group.domain.mail.repository;

import com.group.domain.mail.entity.MailBox;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional
public class MailRepositoryImpl extends QuerydslRepositorySupport implements MailRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    public MailRepositoryImpl(EntityManager entityManager) {
        super(MailBox.class);
        jpaQueryFactory = new JPAQueryFactory(entityManager);
    }
}
