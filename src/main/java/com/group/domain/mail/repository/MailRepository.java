package com.group.domain.mail.repository;

import com.group.domain.mail.entity.MailBox;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface MailRepository extends JpaRepository<MailBox, Integer> {

    @Query(value = "select " +
            "mb.id, " +
            "mb.mail_Title, " +
            "mb.sender_emp_id, " +
            "group_concat(cast(e.emp_name as char)), " +  // CONCAT을 사용하여 이름들을 연결
            "mb.mail_date " +
            "from mailbox mb " +
            "join mailtrans mt on mb.id = mt.mailBox_id " +
            "join employee e on e.id = mt.emp_id " +
            "where mb.sender_emp_id = :senderId and mb.mail_status = 'SENDED' and mb.mail_deleted = 'N' " +
            "group by mb.id, mb.mail_title, mb.sender_emp_id",
            countQuery = "select count(mb.id) " +
                    "from mailbox mb " +
                    "join mailtrans mt on mb.id = mt.mailbox_id " +
                    "join employee e on e.id = mt.emp_id " +
                    "where mb.sender_emp_id = :senderId and mb.mail_status = 'SENDED' and mb.mail_deleted = 'N' ",
            nativeQuery = true)
    Page<Object[]> findMailboxesWithReceiveTypeBySend(@Param("senderId") Integer senderId, Pageable pageable);

    @Query(value = "select " +
            "mb.id, " +
            "mb.mail_Title, " +
            "mb.sender_emp_id, " +
            "group_concat(cast(e.emp_name as char)), " +  // CONCAT을 사용하여 이름들을 연결
            "mb.mail_date " +
            "from mailbox mb " +
            "join mailrecvstore mr on mb.id = mr.mailBox_id " +
            "join employee e on e.id = mr.emp_id " +
            "where mb.sender_emp_id = :senderId and mb.mail_status = 'DRAFT' and mb.mail_deleted = 'N' " +
            "group by mb.id, mb.mail_title, mb.sender_emp_id",
            countQuery = "select count(mb.id) " +
                    "from mailbox mb " +
                    "join mailrecvstore mr on mb.id = mr.mailbox_id " +
                    "join employee e on e.id = mr.emp_id " +
                    "where mb.sender_emp_id = :senderId and mb.mail_status = 'DRAFT'",
            nativeQuery = true)
    Page<Object[]> findMailBoxesWithReceiveTypeByDraft(@Param("senderId") Integer senderId, Pageable pageable);

    @Modifying
    @Query(value = "insert into mailrecvstore (mailbox_id, emp_id) values (:mailboxId, :empId)", nativeQuery = true)
    void saveReceiveStore(@Param("mailboxId") Integer mailboxId, @Param("empId") Integer empId);


}
