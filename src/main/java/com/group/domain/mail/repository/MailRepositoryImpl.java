package com.group.domain.mail.repository;

import com.group.application.mail.dto.*;
import com.group.domain.mail.entity.MailBox;
import com.group.domain.mail.entity.enums.MailStatus;
import com.group.domain.mail.entity.enums.MailTypes;
import com.group.domain.mail.entity.enums.ReceiveType;
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
    public Page<MailTransDTO> findByMailStatus(MailTransDTO mailTransDto, Pageable pageable) {

        List<MailTransDTO> results = jpaQueryFactory.select(new QMailTransDTO(
                        mailBox.id, mailTrans.isFavorite,
                        mailTrans.readStatue, employee.empName, mailBox.mailTitle,
                        mailBox.mailDate))
                .from(mailTrans)
                .join(mailTrans.mailBox, mailBox)
                .join(mailBox.senderEmployee, employee)
                .where(
                        mailTrans.employee.id.eq(mailTransDto.getReceiveEmpId())
                                .and(mailBox.mailStatus.eq(MailStatus.valueOf(mailTransDto.getMailStatus())))
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

    /**
     * receiveTypes(IMPORT, TRASH) 조건에 따라 데이터를 조회
     */
    @Override
    public Page<MailTransDTO> findByMailReceiveType(MailTransDTO mailTransDto, Pageable pageable) {

        List<MailTransDTO> results = jpaQueryFactory.select(new QMailTransDTO(
                        mailBox.id, mailTrans.isFavorite,
                        mailTrans.readStatue, employee.empName, mailBox.mailTitle,
                        mailBox.mailDate))
                .from(mailTrans)
                .join(mailTrans.mailBox, mailBox)
                .join(mailBox.senderEmployee, employee)
                .where(
                        mailTrans.employee.id.eq(mailTransDto.getReceiveEmpId())
                                .and(mailTrans.receiveType.eq(ReceiveType.valueOf(mailTransDto.getReceiveType())))
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
    public Page<MailTransDTO> findByMailTypeSearch(MailTransDTO mailTransDto, Pageable pageable) {

        List<MailTransDTO> results = jpaQueryFactory.select(new QMailTransDTO(
                        mailBox.id, mailTrans.isFavorite,
                        mailTrans.readStatue, employee.empName, mailBox.mailTitle,
                        mailBox.mailDate))
                .from(mailTrans)
                .join(mailTrans.mailBox, mailBox)
                .join(mailBox.senderEmployee, employee)
                .where(
                        mailTrans.employee.id.eq(mailTransDto.getReceiveEmpId())
                                .and(mailTrans.mailTypes.eq(MailTypes.valueOf(mailTransDto.getMailTypes())))
                                .and(mailTrans.receiveType.isNull())
                                .or(mailTrans.receiveType.ne(ReceiveType.valueOf("TRASH")))
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
    public Page<MyMailBoxDTO> findByMyMailBox(MailBoxDTO mailBoxDto, Pageable pageable) {

        // 받은 메일함 (전체 메일)
        List<MyMailBoxDTO> results = jpaQueryFactory.select(new QMyMailBoxDTO(
                        mailTrans.mailBox.id,
                        mailTrans.isFavorite,
                        mailTrans.readStatue,
                        employee.empName,
                        mailBox.mailTitle, mailBox.mailDate))
                .from(mailTrans)
                .join(mailTrans.mailBox, mailBox)
                .join(mailBox.senderEmployee, employee)
                .where(
                        mailTrans.employee.id.eq(mailBoxDto.getSenderEmployeeId())
                                .and(mailTrans.receiveType.isNull())
                                .or(mailTrans.receiveType.ne(ReceiveType.valueOf("TRASH")))
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
    /**
     *
     * @param mailBoxDto 메일 내부의 mailType 에 따라 조회
     * @param pageable
     * @return
     */
    public Page<MyMailBoxDTO> findByMailType(MailBoxDTO mailBoxDto, Pageable pageable) {

        List<MyMailBoxDTO> results = jpaQueryFactory.select(new QMyMailBoxDTO(
                        mailTrans.mailBox.id,
                        mailTrans.isFavorite,
                        mailTrans.readStatue,
                        employee.empName,
                        mailBox.mailTitle, mailBox.mailDate))
                .from(mailTrans)
                .join(mailTrans.mailBox, mailBox)
                .join(mailBox.senderEmployee, employee)
                .where(
                        mailTrans.employee.id.eq(mailBoxDto.getSenderEmployeeId())
                                .and(mailTrans.mailTypes.eq(MailTypes.valueOf(mailBoxDto.getMailType())))
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
}
