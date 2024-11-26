package com.group.domain.mail.repository;

import com.group.application.mail.dto.MailBoxDTO;
import com.group.application.mail.dto.QMailBoxDTO;
import com.group.domain.hr.entity.QEmployee;
import com.group.domain.mail.entity.MailBox;
import com.group.domain.mail.entity.QMailBox;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import static com.group.domain.hr.entity.QEmployee.*;
import static com.group.domain.mail.entity.QMailBox.*;


@Repository
@Transactional
public class MailRepositoryImpl extends QuerydslRepositorySupport implements MailRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    public MailRepositoryImpl(EntityManager entityManager) {
        super(MailBox.class);
        jpaQueryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public MailBoxDTO findByAll(MailBoxDTO mailBoxDTO) {

        // TODO
       return jpaQueryFactory.select(new QMailBoxDTO(
                mailBox.id, mailBox.senderEmployee.id,
                mailBox.mailTitle, mailBox.mailContent, employee.empName, mailBox.mailDate))
                .from(mailBox)
                .join(mailBox.senderEmployee, employee)
                .where(mailBox.senderEmployee.id.eq(mailBoxDTO.getSenderEmployeeId()))
                .fetchFirst();
    }
}
