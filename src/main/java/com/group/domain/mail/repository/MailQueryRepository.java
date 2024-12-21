package com.group.domain.mail.repository;

import com.group.application.hr.dto.EmployeeDTO;
import com.group.application.mail.dto.MailBoxDTO;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
@RequiredArgsConstructor
public class MailQueryRepository {

    private final EntityManager em;

    //
    Page<MailBoxDTO> findAll(EmployeeDTO employeeDto, Pageable pageable) {


        /*
            # 내게 쓴 메일함만 조회
            select * from mailbox where mail_type = 'TOME' and sender_emp_id = 29;
            select * from mailbox where sender_emp_id = 29;
         */
        // TODO
        return null;
    }

}

