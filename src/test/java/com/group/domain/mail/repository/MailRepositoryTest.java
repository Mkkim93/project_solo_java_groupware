package com.group.domain.mail.repository;

import com.group.application.hr.dto.EmployeeDTO;
import com.group.application.mail.dto.MailBoxDTO;
import com.group.application.mail.dto.MyMailBoxDTO;
import com.group.domain.hr.repository.EmployeeRepository;
import com.group.domain.mail.entity.MailBox;
import com.group.domain.mail.entity.enums.MailType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

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
    @DisplayName("메일 타입으로 조회하기 (SENT, TOME, INBOX)")
    void findMailType() {
        Integer empId = 29;
        PageRequest pageable = PageRequest.of(0, 5);
        MailBoxDTO dto = new MailBoxDTO();
        dto.setSenderEmployeeId(empId);
        dto.setMailType(MailType.TOME);
        Page<MyMailBoxDTO> byMailType = mailRepositoryImpl.findByMailType(dto, pageable);

        List<MyMailBoxDTO> list = byMailType.stream().toList();
        for (MyMailBoxDTO myMailBoxDTO : list) {
            System.out.println("myMailBoxDTO. = " + myMailBoxDTO.getMailTitle());
            System.out.println("myMailBoxDTO.getMailTitle() = " + myMailBoxDTO.getMailTitle());
        }
    }


}