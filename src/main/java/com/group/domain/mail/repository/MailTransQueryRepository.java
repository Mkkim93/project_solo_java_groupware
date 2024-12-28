package com.group.domain.mail.repository;

import com.group.domain.mail.entity.QMailTrans;
import com.group.domain.mail.entity.enums.ReceiveType;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.group.domain.mail.entity.QMailTrans.*;

@Repository
@Transactional
@RequiredArgsConstructor
public class MailTransQueryRepository {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public void saveV2(Integer mailBoxId, List<Integer> empIds) {

        String mailTypes = "INBOX";
        String sql = "INSERT INTO mailtrans (mailbox_id, emp_id, mail_types) VALUES (:mailboxId, :empId, :mailTypes)";
        for (Integer empId : empIds) {
            em.createNativeQuery(sql)
                    .setParameter("mailboxId", mailBoxId)
                    .setParameter("empId", empId)
                    .setParameter("mailTypes", mailTypes)
                    .executeUpdate();
        }
    }

    public void bulkTrashUpdate(List<Integer> mailBoxIds) {
        queryFactory.update(mailTrans)
                .set(mailTrans.receiveType, ReceiveType.TRASH)
                .where(mailTrans.mailBox.id.in(mailBoxIds))
                .execute();
        em.flush();
        em.clear();
    }

}
