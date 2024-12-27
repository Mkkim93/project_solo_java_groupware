package com.group.domain.mail.repository;

import com.group.domain.mail.entity.MailTrans;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface MailTransRepository extends JpaRepository<MailTrans, Integer> {

    @Modifying
    @Query("update MailTrans t set t.receiveType = 'TRASH' where t.mailBox.id = :id")
    Integer mailTransByTrash(@Param("id") Integer id);

    @Modifying
    @Query("update MailTrans t set t.isDeleted = 'Y' where t.mailBox.id = :mailBoxId")
    Integer deleteMailTrans(@Param("mailBoxId") Integer mailBoxId);
}
