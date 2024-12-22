package com.group.domain.mail.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
@RequiredArgsConstructor
public class MailTransQueryRepository {

    private final EntityManager em;

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

}
