package com.group.application.mail.service;

import com.group.application.mail.dto.MailTransDTO;
import com.group.domain.mail.entity.MailTrans;
import com.group.domain.mail.repository.MailRepositoryImpl;
import com.group.domain.mail.repository.MailTransQueryRepository;
import com.group.domain.mail.repository.MailTransRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class MailTransService {

    private final MailRepositoryImpl mailRepositoryImpl;
    private final MailTransQueryRepository mailTransQueryRepository;
    private final MailTransRepository mailTransRepository;

    public void save(Integer mailBoxId, Integer receiveEmpId) {
        String TOME = "TOME";
        MailTrans mailTrans = new MailTrans();
        mailTrans.setReceiveMail(mailBoxId, receiveEmpId, TOME);
        mailTransRepository.save(mailTrans);
    }

    public void saveV2(Integer mailBoxId, List<Integer> empId) {
        mailTransQueryRepository.saveV2(mailBoxId, empId);
    }

    public Page<MailTransDTO> typeByMailSearch(MailTransDTO mailTransDto, Pageable pageable) {
        return mailRepositoryImpl.findByMailTypeSearch(mailTransDto, pageable);
    }

    public Page<MailTransDTO> receiveTypeBySearch(MailTransDTO mailTransDto, Pageable pageable) {
        return mailRepositoryImpl.findByMailReceiveType(mailTransDto, pageable);
    }

    public Page<MailTransDTO> findByMailStatus(MailTransDTO mailTransDto, Pageable pageable) {
        return mailRepositoryImpl.findByMailStatus(mailTransDto, pageable);
    }
}
