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

    /*public Page<MailBoxDTO> find(Integer senderId, Pageable pageable) {
        String jpql = "SELECT new com.group.application.mail.dto.MailBoxDTO( " +
                "mb.id, " +
                "mb.mailTitle, " +
                "mb.senderEmployee.id, " +
                "GROUP_CONCAT(CAST(e.empName AS string)), " +  // empName을 결합
                "FROM MailBox mb " +
                "JOIN MailTrans mt ON mb.id = mt.mailBox.id " +
                "JOIN Employee e ON mt.employee.id = e.id " +
                "WHERE mb.senderEmployee.id = :senderId " +
                "GROUP BY mb.id, mb.mailTitle, mb.mailContent, mb.senderEmployee.id";;

        // 메일 목록 조회 쿼리 실행
        TypedQuery<MailBoxDTO> query = em.createQuery(jpql, MailBoxDTO.class);
        query.setParameter("senderId", senderId);

        // 페이지네이션 설정
        query.setFirstResult((int) pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());

        List<MailBoxDTO> mailBoxDTOs = query.getResultList();

        // 전체 건수 조회 (페이지 네비게이션을 위한 전체 결과 수)
        String countJpql = "SELECT COUNT(mb) " +
                "FROM MailBox mb " +
                "JOIN MailTrans mt ON mb.id = mt.mailBox.id " +
                "WHERE mb.senderEmployee.id = :senderId";

        TypedQuery<Long> countQuery = em.createQuery(countJpql, Long.class);
        countQuery.setParameter("senderId", senderId);
        Long totalCount = countQuery.getSingleResult();

        // Page 객체로 결과 반환
        return new PageImpl<>(mailBoxDTOs, pageable, totalCount);
    }*/

}

