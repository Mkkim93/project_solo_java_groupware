package com.group.domain.mail.repository;

import com.group.application.mail.dto.MailBoxDTO;
import com.group.application.mail.dto.MyMailBoxDTO;
import com.group.application.mail.dto.QMailBoxDTO;
import com.group.application.mail.dto.QMyMailBoxDTO;
import com.group.domain.hr.entity.QEmployee;
import com.group.domain.mail.entity.MailBox;
import com.group.domain.mail.entity.QMailBox;
import com.group.domain.mail.entity.QMailTrans;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.group.domain.hr.entity.QEmployee.*;
import static com.group.domain.mail.entity.QMailBox.*;
import static com.group.domain.mail.entity.QMailTrans.*;


@Repository
@Transactional
public class MailRepositoryImpl extends QuerydslRepositorySupport implements MailRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    public MailRepositoryImpl(EntityManager entityManager) {
        super(MailBox.class);
        jpaQueryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public Page<MyMailBoxDTO> findByMyMailBox(MailBoxDTO mailBoxDto, Pageable pageable) {
        /*
            # 받은 메일함에서 보낸 유저의 id 를 조회
            select mt.mailbox_id, mb.mail_title, mb.mail_content, mb.sender_emp_id, e.emp_name
            from mailtrans mt, mailbox mb, employee e
            where mt.mailbox_id = mb.id and mb.sender_emp_id = e.id and mt.emp_id = 29;
         */
        List<MyMailBoxDTO> results = jpaQueryFactory.select(new QMyMailBoxDTO(
                        mailTrans.mailBox.id,
                        mailTrans.isFavorite,
                        mailTrans.readType,
                        employee.empName,
                        mailBox.mailTitle, mailBox.mailDate))
                .from(mailTrans)
                .join(mailTrans.mailBox, mailBox)
                .join(mailBox.senderEmployee, employee)
                .where(
                        mailTrans.employee.id.eq(mailBoxDto.getSenderEmployeeId())
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> count = jpaQueryFactory.select(mailTrans.count())
                .from(mailTrans)
                .join(mailTrans.mailBox, mailBox)
                .join(mailBox.senderEmployee, employee);
        return PageableExecutionUtils.getPage(results, pageable, () -> count.fetchCount());
    }

    @Override
    public MailBoxDTO findByOne(Integer id) {

        return jpaQueryFactory.select(new QMailBoxDTO(
                mailBox.id, mailBox.senderEmployee.id,
                mailBox.mailTitle, mailBox.mailContent, employee.empName, mailBox.mailDate))
                .from(mailBox)
                .join(mailBox.senderEmployee, employee)
                .where(mailBox.id.eq(id)).fetchOne();
    }
}
