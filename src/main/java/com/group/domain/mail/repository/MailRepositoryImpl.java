package com.group.domain.mail.repository;

import com.group.application.mail.dto.*;
import com.group.domain.hr.entity.QEmployee;
import com.group.domain.mail.entity.MailBox;
import com.group.domain.mail.entity.QMailRecvStore;
import com.group.domain.mail.entity.enums.*;
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
import static com.group.domain.mail.entity.QMailRecvStore.*;
import static com.group.domain.mail.entity.QMailTrans.*;


@Repository
@Transactional
public class MailRepositoryImpl extends QuerydslRepositorySupport implements MailRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;
    private final MailTransRepository mailTransRepository;

    public MailRepositoryImpl(EntityManager entityManager, MailTransRepository mailTransRepository) {
        super(MailBox.class);
        jpaQueryFactory = new JPAQueryFactory(entityManager);
        this.mailTransRepository = mailTransRepository;
    }

    @Override
    public List<MailBoxDTO> findByOneV2toTO(Integer mailBoxId, Integer empId) {

        QMailRecvStore mailRecvStore1 = mailRecvStore;
        QMailRecvStore mailRecvStore2 = new QMailRecvStore("mailRecvStore2");

        QEmployee employee1 = employee;
        QEmployee employee2 = new QEmployee("employee2");
        QEmployee employee3 = new QEmployee("employee3");

        List<MailBoxDTO> results = jpaQueryFactory.select(new QMailBoxDTO(
                        mailBox.id,
                        mailBox.senderEmployee.id,
                        mailBox.mailTitle,
                        mailBox.mailContent,
                        employee3.empName, // 발신자 이름
                        employee3.empEmail, // 발신자 이메일
                        employee1.empEmail, // 수신자 이메일
                        employee1.empName, // 수신자 이름
                        employee2.empEmail, // 참조자 이메일
                        employee2.empName, // 참조자 이름
                        mailBox.mailDate))
                .from(mailBox)

                .leftJoin(employee3).on(mailBox.senderEmployee.id.eq(employee3.id))

                .leftJoin(mailRecvStore1).on(mailBox.id.eq(mailRecvStore1.mailBox.id)
                        .and(mailRecvStore1.iscc.eq(ISCC.TO)))
                .leftJoin(employee1).on(mailRecvStore1.employee.id.eq(employee1.id))

                .leftJoin(mailRecvStore2).on(mailBox.id.eq(mailRecvStore2.mailBox.id)
                        .and(mailRecvStore2.iscc.eq(ISCC.CC)))
                .leftJoin(employee2).on(mailRecvStore2.employee.id.eq(employee2.id))
                .where(
                        mailBox.id.eq(mailBoxId)
                                /*.and(employee1.id.eq(empId))*/
                )
                .groupBy(mailBox.id,
                        mailBox.senderEmployee.id,
                        mailBox.mailTitle,
                        mailBox.mailContent,
                        mailBox.mailDate,
                        employee1.empName,
                        employee2.empName,
                        employee3.empName,
                        employee1.empEmail,
                        employee2.empEmail,
                        employee3.empEmail)
                .fetch();

        return results;
    }


    /**
     * receiveTypes(IMPORT, TRASH) 조건에 따라 데이터를 조회
     */
    @Override
    public Page<MailTransDTO> findByMailReceiveType(MailTransDTO mailTransDto, Pageable pageable) {

        List<MailTransDTO> results = jpaQueryFactory.select(new QMailTransDTO(
                        mailBox.id,
                        mailTrans.isFavorite,
                        mailTrans.readStatue,
                        employee.empName,
                        mailBox.mailTitle,
                        mailBox.mailDate))
                .from(mailTrans)
                .join(mailTrans.mailBox, mailBox)
                .join(mailBox.senderEmployee, employee)
                .where(
                        mailTrans.employee.id.eq(mailTransDto.getReceiveEmpId())

                                .and(mailTrans.receiveType.eq(ReceiveType.valueOf(mailTransDto.getReceiveType())))
                                .and(mailTrans.isDeleted.eq(IsDeleted.N))
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
                                .and(
                                        mailTrans.mailTypes.eq(MailTypes.valueOf(mailTransDto.getMailTypes())))
                                .and((
                                        mailTrans.receiveType.isNull())
                                        .or(
                                                mailTrans.receiveType.ne(ReceiveType.TRASH))).and(mailTrans.isDeleted.eq(IsDeleted.N))
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

                                .and(
                                        mailTrans.receiveType.isNull()
                                                .or(mailTrans.receiveType.ne(ReceiveType.valueOf("TRASH")))
                                ).and(mailTrans.isDeleted.eq(IsDeleted.N))
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



   /* @Override
    public MailBoxDTO findByOne(Integer id) {

        return jpaQueryFactory.select(new QMailBoxDTO(
                mailBox.id, mailBox.senderEmployee.id,
                mailBox.mailTitle, mailBox.mailContent, employee.empName, mailBox.mailDate, employee.empEmail))
                .from(mailBox)
                .join(mailBox.senderEmployee, employee)
                .where(
                        mailBox.id.eq(id)
                ).fetchOne();
    }*/
    /**
     *
     * @param mailBoxDto 메일 내부의 mailType 에 따라 조회
     * @param pageable
     * @return
     */
    /*public Page<MyMailBoxDTO> findByMailType(MailBoxDTO mailBoxDto, Pageable pageable) {

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
    }*/

