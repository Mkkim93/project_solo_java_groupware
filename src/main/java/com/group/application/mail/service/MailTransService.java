package com.group.application.mail.service;

import com.group.application.mail.dto.MailBoxDTO;
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
import org.springframework.web.context.support.GroovyWebApplicationContext;

import java.util.ArrayList;
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

    public void saveAll(Integer mailBoxId, List<Integer> empIds) {
        String mailTypes = "INBOX";
        List<MailTrans> mailTransList = new ArrayList<>();
        for (Integer empId : empIds) {
            MailTrans result = new MailTrans(mailBoxId, empId, mailTypes);
            mailTransList.add(result);
        }
        mailTransRepository.saveAll(mailTransList);
    }

    public Page<MailTransDTO> typeByMailSearch(MailTransDTO mailTransDto, Pageable pageable) {
        return mailRepositoryImpl.findByMailTypeSearch(mailTransDto, pageable);
    }

    public Page<MailTransDTO> receiveTypeBySearch(MailTransDTO mailTransDto, Pageable pageable) {
        return mailRepositoryImpl.findByMailReceiveType(mailTransDto, pageable);
    }

    public void mailTransByTrash(Integer mailBoxId) {
        mailTransRepository.mailTransByTrash(mailBoxId);
    }

    public void deleteMail(Integer mailBoXId) {
        mailTransRepository.deleteMailTrans(mailBoXId);
    }

    public void checkBoxTrashMailBoxId(List<Integer> mailBoxIds) {
        mailTransQueryRepository.bulkTrashUpdate(mailBoxIds);
    }

    /*public void favoriteMail(Integer mailBoxId) {
        mailTransRepository.favoriteMailTrans(mailBoxId);
    }*/
}
