package com.group.domain.mail.repository;

import com.group.application.hr.dto.EmployeeDTO;
import com.group.application.hr.dto.EmployeeEmailDto;
import com.group.application.mail.dto.MailBoxDTO;
import com.group.domain.mail.entity.MailBox;
import com.group.domain.mail.entity.enums.ISCC;
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

    public void saveMailBoxIdAndReceiveId(Integer mailBoxId, List<Integer> empIds, ISCC iscc) {

        String sql = "INSERT INTO mailrecvstore (mailbox_id, emp_id, is_cc) VALUES (:mailboxId, :empId, :iscc)";

        for (Integer empId : empIds) {
            em.createNativeQuery(sql)
                    .setParameter("mailboxId", mailBoxId)
                    .setParameter("empId", empId)
                    .setParameter("iscc", iscc.name())
                    .executeUpdate();
        }
    }

    public void saveMailBoxIdAndReceiveIdCC(Integer mailBoxId, List<Integer> empIds, ISCC iscc) {

        String sql = "INSERT INTO mailrecvstore (mailbox_id, emp_id, is_cc) VALUES (:mailboxId, :empId, :iscc)";

        for (Integer empId : empIds) {
            em.createNativeQuery(sql)
                    .setParameter("mailboxId", mailBoxId)
                    .setParameter("empId", empId)
                    .setParameter("iscc", iscc.name())
                    .executeUpdate();
        }
    }

    public List<EmployeeEmailDto> findByMailReceiverTo(Integer mailBoxId) {

        return em.createQuery("select e.empName, e.empEmail " +
                "from MailRecvStore mr, Employee e " +
                "where mr.employee.id = e.id and mr.mailBox.id = :mailBoxId " +
                        "and mr.iscc = 'TO'", EmployeeEmailDto.class)
                .setParameter("mailBoxId", mailBoxId).getResultList();
    }

    public List<EmployeeEmailDto> findByMailReceiverCC(Integer mailBoxId) {

        return em.createQuery("select e.empName, e.empEmail " +
                        "from Employee e, MailRecvStore mr " +
                        "where mr.employee.id = e.id and mr.mailBox.id = :mailBoxId " +
                        "and mr.iscc = 'CC'", EmployeeEmailDto.class)
                .setParameter("mailBoxId", mailBoxId).getResultList();
    }
}

