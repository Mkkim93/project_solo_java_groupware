package com.group.application.mail.service;

import com.group.application.hr.dto.EmployeeDTO;
import com.group.application.mail.dto.MailBoxDTO;
import com.group.application.mail.dto.MyMailBoxDTO;
import com.group.domain.hr.entity.Employee;
import com.group.domain.mail.entity.MailBox;
import com.group.domain.mail.entity.enums.MailType;
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
        Integer id = 9;
        MailBoxDTO byId = mailService.detail(id);
        System.out.println("byId.getId() = " + byId.getId());
    }

    @Test
    @DisplayName("메일함 조회")
    void searchByMailTypeList() {
        MailBoxDTO dto = new MailBoxDTO();
        dto.setSenderEmployeeId(29);
        dto.setMailType(MailType.TOME);
        PageRequest page = PageRequest.of(0, 15);
        mailService.searchByMailTypeList(dto, page);

    }

}