package com.group.domain.mail.repository;

import com.group.application.mail.dto.MailBoxDTO;
import com.group.application.mail.dto.MailTransDTO;
import com.group.application.mail.dto.MyMailBoxDTO;
import com.group.application.mail.dto.SendMailBoxDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface MailRepositoryCustom {

    Page<MyMailBoxDTO> findByMyMailBox(MailBoxDTO mailBoxDto, Pageable pageable);

    MailBoxDTO findByOne(Integer id);

    Page<MailTransDTO> findByMailTypeSearch(MailTransDTO mailTransDto, Pageable pageable);

    Page<MailTransDTO> findByMailReceiveType(MailTransDTO mailTransDto, Pageable pageable);

}
