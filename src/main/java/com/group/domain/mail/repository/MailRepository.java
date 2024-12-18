package com.group.domain.mail.repository;

import com.group.domain.mail.entity.MailBox;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface MailRepository extends JpaRepository<MailBox, Integer> {

    @Query(value = "SELECT mb.id AS id, " +
            "mb.mail_title AS mailTitle, " +
            "mb.sender_emp_id AS senderEmpId, " +
            "e.emp_name AS senderName, " +
            "mb.mail_date AS senderDate " +
            "FROM mailrecvstore mr " +
            "JOIN mailbox mb ON mr.mailbox_id = mb.id " +
            "JOIN employee e ON mb.sender_emp_id = e.id " +
            "WHERE mr.emp_id = :empId",
            nativeQuery = true)
    List<Object[]> findReceivedMails(@Param("empId") Integer empId);

    @Modifying
    @Query(value = "INSERT INTO mailrecvstore (mailbox_id, emp_id) VALUES (:mailboxId, :empId)", nativeQuery = true)
    void saveReceiveStore(@Param("mailboxId") Integer mailboxId, @Param("empId") Integer empId);
}
