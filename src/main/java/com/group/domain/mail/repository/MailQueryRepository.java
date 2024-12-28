package com.group.domain.mail.repository;

import com.group.application.hr.dto.EmployeeDTO;
import com.group.application.mail.dto.MailBoxDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
@RequiredArgsConstructor
public class MailQueryRepository {

    private final EntityManager em;

    public void saveMailBoxIdAndReceiveId(Integer mailBoxId, List<Integer> empIds) {

        String sql = "INSERT INTO mailrecvstore (mailbox_id, emp_id) VALUES (:mailboxId, :empId)";

        for (Integer empId : empIds) {
            em.createNativeQuery(sql)
                    .setParameter("mailboxId", mailBoxId)
                    .setParameter("empId", empId)
                    .executeUpdate();
        }
    }
}

