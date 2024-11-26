package com.group.application.mail.service;

import com.group.application.hr.dto.EmployeeDTO;
import com.group.application.mail.dto.MailBoxDTO;
import com.group.domain.hr.entity.Employee;
import com.group.domain.mail.entity.MailBox;
import com.group.domain.mail.repository.MailRepositoryImpl;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MailServiceTest {

    @Autowired
    MailRepositoryImpl mailRepositoryImpl;

    @Autowired
    EntityManager entityManager;

    @Autowired MailService mailService;


    @Test
    public void testFetchReceiverEmployees() {
        MailBox mailBox = entityManager.find(MailBox.class, 1);
        List<Employee> employees = mailBox.getReceiverEmployees();

        assertNotNull(employees);
        assertFalse(employees.isEmpty());
    }

    @Test
    @DisplayName("받은 메일함 테스트")
    void testFindSendMail() {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(6);
        List<MailBoxDTO> result = mailService.findAllSendMailBox(employeeDTO);
        for (MailBoxDTO mailBoxDTO : result) {
            System.out.println("mailBoxDTO.getSenderEmployeeId() = " + mailBoxDTO.getSenderEmployeeId());
            System.out.println("mailBoxDTO.getMailTitle() = " + mailBoxDTO.getMailTitle());
            System.out.println("mailBoxDTO.getMailContent() = " + mailBoxDTO.getMailContent());
        }
    }


    @Test
    @DisplayName("step 1 : MailBox 에 메일 작성")
    void writeMailBox() {
        MailBoxDTO mailBoxDTO = new MailBoxDTO("메일 제목12", "메일 내용12", 1, "alsrb362@daum.net");
        MailBoxDTO result = mailService.mailWrite(mailBoxDTO);
        assertThat(mailBoxDTO).isEqualTo(result);
    }

    @Test
    @DisplayName("step 2 : recevmailstore 에 메일 저장 (받을 사람 지정)")
    void writeReceiveMailStore() {
        //TODO
    }

    @Test
    @DisplayName("step 3 : mailtrans 에 메일 기록 최종 저장")
    void writeMailTrans() {
        //TODO
    }

}