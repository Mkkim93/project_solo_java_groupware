package com.group.domain.mail.repository;

import com.group.application.hr.dto.EmployeeDTO;
import com.group.application.hr.dto.EmployeeEmailDto;
import com.group.application.mail.dto.MailBoxDTO;
import com.group.application.mail.dto.MailReplyDTO;
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

import java.time.LocalDateTime;
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

    // 답장 메일을 저장하고 mailbox 의 pk ? 아니면 parent_id 를 꺼내서 recv, trans 에 저장해야 할듯
    public MailReplyDTO replyMail(MailReplyDTO mailReplyDto) {

        mailReplyDto.setMailDate(LocalDateTime.now());
        mailReplyDto.setMailDeleted("N");

       String sql = "insert into mailbox(mail_title, mail_content, mail_date, sender_emp_id, " +
               "mail_status, mail_deleted, mail_parent_id) " +
               "values(?, ?, ?, ?, ?, ?, ?)";

       em.createNativeQuery(sql)
               .setParameter(1, mailReplyDto.getMailTitle())
               .setParameter(2, mailReplyDto.getMailContent())
               .setParameter(3, mailReplyDto.getMailDate())
               .setParameter(4, mailReplyDto.getSenderEmpId())
               .setParameter(5, mailReplyDto.getMailStatus())
               .setParameter(6, mailReplyDto.getMailDeleted())
               .setParameter(7, mailReplyDto.getMailParentId())
               .executeUpdate();

       // TODO parent id 인지 mailbox id 를 반환할지 헷갈림
        return mailReplyDto;
    }
}

