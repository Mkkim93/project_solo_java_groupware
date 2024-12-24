package com.group.application.mail.service;

import com.group.application.mail.dto.MailBoxDTO;
import com.group.application.mail.dto.MyMailBoxDTO;
import com.group.domain.hr.entity.Employee;
import com.group.domain.mail.entity.MailBox;
import com.group.domain.mail.entity.enums.MailStatus;
import com.group.domain.mail.repository.MailRepositoryImpl;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MailServiceTest {

    @Autowired
    MailRepositoryImpl mailRepositoryImpl;

    @Autowired
    EntityManager entityManager;

    @Autowired MailService mailService;


    @Test
    void sendMailBox() {
        MailBoxDTO mailBoxDTO = new MailBoxDTO();
        mailBoxDTO.setSenderEmployeeId(29);
        PageRequest pageRequest = PageRequest.of(0, 10);
        Page<MailBoxDTO> results = mailService.findReceiveTypeBySend(mailBoxDTO, pageRequest);
        results.stream().toList().forEach(System.out::println);
    }

    @Test
    void sendMail() {
        MailBoxDTO mailBoxDTO = new MailBoxDTO();
        mailBoxDTO.setSenderEmployeeId(29);
        PageRequest pageRequest = PageRequest.of(0, 10);
        mailService.findReceiveTypeBySend(mailBoxDTO, pageRequest);
    }


    @Test
    public void testFetchReceiverEmployees() {
        MailBox mailBox = entityManager.find(MailBox.class, 1);
        List<Employee> employees = mailBox.getReceiverEmployees();

        assertNotNull(employees);
        assertFalse(employees.isEmpty());
    }

    @Test
    @DisplayName("전체 메일함 조회")
    void findAllMyMailBox() {
        MailBoxDTO dto = new MailBoxDTO();
        dto.setSenderEmployeeId(29);
        PageRequest page = PageRequest.of(0, 15);
        Page<MyMailBoxDTO> result = mailService.findByMyMailBox(dto, page);
        for (MyMailBoxDTO v : result) {
            System.out.println("v = " + v);
        }
    }


    @Test
    @DisplayName("step 1 : MailBox 에 메일 작성")
    void writeMailBox() {

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

    @Test
    @DisplayName("메일 상세 페이지 조회 시 id 값 (service)")
    void mailDetailId() {

    }

    @Test
    @DisplayName("여러명의 사용자에게 메일 전송")
    void sendMails() {
        MailBoxDTO dto = new MailBoxDTO();
        dto.setReceiverEmail("alsrb362@daum.net");
        dto.setReceiverEmail("rainbow213@nate.com");
        dto.setMailTitle("여러명 메일 전송 제목");
        dto.setMailContent("여러명 메일 전송 내용");
        dto.setSenderEmployeeId(29);
        mailService.sendMailToRecipient(dto);
    }

    @Test
    @DisplayName("여러명 사용자에게 메일 전송 (쉼표)")
    void sendMailV2() {
        MailBoxDTO dto = new MailBoxDTO();
        dto.setReceiverEmail("alsrb362@daum.net, rainbow213@nate.com");
        dto.setMailTitle("여러명 메일 전송 제목2 (쉼표)");
        dto.setMailContent("여러명 메일 전송 내용2 (쉼표)");
        dto.setSenderEmployeeId(29);
        mailService.sendMailToRecipient(dto);
    }

    @Test
    @DisplayName("여러명 사용자에게 메일 전송 (쉼표 + endpoint 까지 테스트, mailtrans)")
    void sendMailV3() {
        MailBoxDTO dto = new MailBoxDTO();
        dto.setReceiverEmail("alsrb362@daum.net, rainbow213@nate.com");
        dto.setMailTitle("여러명 메일 전송 제목2 (쉼표)");
        dto.setMailContent("여러명 메일 전송 내용2 (쉼표)");
        dto.setSenderEmployeeId(29);
        mailService.sendMailToRecipient(dto);
    }

    @Test
    @DisplayName("보낸 메일함 조회 시 데이터 누락 검증")
    void sendMailV4() {
        MailBoxDTO dto = new MailBoxDTO();
        dto.setMailStatus(MailStatus.valueOf("SENDED"));
        dto.setSenderEmployeeId(29);
        PageRequest pageRequest = PageRequest.of(0, 10);
        Page<MailBoxDTO> results = mailService.findReceiveTypeBySend(dto, pageRequest);
        List<MailBoxDTO> list = results.stream().toList();
        for (MailBoxDTO mailBoxDTO : list) {
            System.out.println("mailBoxDTO.getSenderDate().getClass() = " + mailBoxDTO.getMailDate().getClass());
        }

    }
}