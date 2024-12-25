package com.group.domain.mailfile.repository;

import com.group.application.mail.dto.MailBoxDTO;
import com.group.application.mailfile.dto.MailFileDTO;
import com.group.domain.mailfile.entity.MailFileStore;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MailFileCustomRepository {

    List<MailFileDTO> findByMailFileList(MailBoxDTO mailBoxDto);
}
