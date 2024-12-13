package com.group.domain.mail.repository;

import com.group.application.mail.dto.MailBoxDTO;
import com.group.application.mail.dto.MyMailBoxDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface MailRepositoryCustom {

    // 임시

    Page<MyMailBoxDTO> findByMyMailBox(MailBoxDTO mailBoxDto, Pageable pageable);

    MailBoxDTO findByOne(Integer id);
}
