package com.group.domain.mail.repository;

import com.group.application.hr.dto.EmployeeDTO;
import com.group.application.mail.dto.MailBoxDTO;
import com.group.application.mail.dto.MailTransDTO;
import com.group.domain.hr.repository.EmployeeRepository;
import com.group.domain.mail.entity.MailBox;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

@SpringBootTest
class MailRepositoryTest {

    @Autowired
    MailRepository mailRepository;

    @Autowired
    MailRepositoryImpl mailRepositoryImpl;

    @Autowired
    EmployeeRepository employeeRepository;


    @Test
    void findAll() {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(6);
        mailRepository.findReceivedMails(employeeDTO.getId());
    }



    @Test
    @DisplayName("나의 id 조회하기")
    void findByMyId() {
        MailBoxDTO mailBoxDTO = new MailBoxDTO();
        mailBoxDTO.setSenderEmployeeId(1);
        MailBox myId = mailRepository.findById(mailBoxDTO.getSenderEmployeeId()).get();
        Assertions.assertThat(myId.getSenderEmployee()).isEqualTo(1);
    }


    @Test
    @DisplayName("메일 상세 페이지 조회 시 id 값 (repository)")
    void mailDetailId() {
        Integer id = 9;
        MailBoxDTO byOne = mailRepositoryImpl.findByOne(id);
        System.out.println("byOne = " + byOne.getId());
        System.out.println("byOne.getMailTitle() = " + byOne.getMailTitle());
        System.out.println("byOne.getMailContent() = " + byOne.getMailContent());
    }

    @Test
    @DisplayName("메일 조회 (INBOX, TOME)")
    void findMailTome() {
        MailTransDTO mailTransDto = new MailTransDTO();
        // mailTransDto.setMailTypes("TOME");
        mailTransDto.setMailTypes("INBOX");
        mailTransDto.setReceiveEmpId(29);
        PageRequest page = PageRequest.of(0, 5);
        Page<MailTransDTO> results = mailRepositoryImpl.findByMailTypeSearch(mailTransDto, page);

        results.stream().toList().forEach(System.out::println);
    }

    @Test
    @DisplayName("메일 조회 (TRASH, IMPORT)")
    void findMailTrashAndImport() {
        MailTransDTO mailTransDTO = new MailTransDTO();
        mailTransDTO.setReceiveType("TRASH");
        mailTransDTO.setReceiveEmpId(29);
        PageRequest page = PageRequest.of(0, 5);
        Page<MailTransDTO> results = mailRepositoryImpl.findByMailReceiveType(mailTransDTO, page);

        results.stream().toList().forEach(System.out::println);
    }
}


