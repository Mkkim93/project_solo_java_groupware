package com.group.domain.mail.repository;

import com.group.application.hr.dto.EmployeeDTO;
import com.group.application.mail.dto.MailBoxDTO;
import com.group.domain.hr.entity.Employee;
import com.group.domain.hr.repository.EmployeeRepository;
import com.group.domain.mail.entity.MailBox;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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
    @DisplayName("메일 작성 시 조회 데이터 바인딩을 위한 쿼리 테스트 findAll")
    void findAllWriter() {
        MailBoxDTO mailBoxDTO = new MailBoxDTO();
        mailBoxDTO.setSenderEmployeeId(1);
        MailBoxDTO byAll = mailRepositoryImpl.findByAll(mailBoxDTO);
        System.out.println("byAll = " + byAll.getId());
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
    @DisplayName("내게 쓰기 저장")
    void meToMailSave() {
        MailBoxDTO mailBoxDTO = new MailBoxDTO("내게 쓰기 테스트", "내게 쓰기 내용", 1, "king00314@naver.com");
        Employee byEmpEmail = employeeRepository.findByEmpEmail(mailBoxDTO.getReceiverEmail());
        System.out.println(byEmpEmail.getEmpEmail());
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



}