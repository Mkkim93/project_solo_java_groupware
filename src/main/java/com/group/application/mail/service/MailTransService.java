package com.group.application.mail.service;

import com.group.application.mail.dto.MailTransDTO;
import com.group.domain.mail.entity.MailTrans;
import com.group.domain.mail.repository.MailTransRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class MailTransService {

    private final MailTransRepository mailTransRepository;

    public void save(Integer mailBoxId, Integer receiveEmpId) {
        MailTrans mailTrans = new MailTrans();
        mailTrans.setReceiveMail(mailBoxId, receiveEmpId);
        mailTransRepository.save(mailTrans);
    }
}
