package com.group.domain.mail.repository;

import com.group.domain.mail.entity.MailTrans;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MailTransRepository extends JpaRepository<MailTrans, Integer> {
}
