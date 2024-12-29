/*
package com.group.domain.mail.repository;

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

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
class MailRepositoryTest {

    @Autowired
    MailRepository mailRepository;

    @Autowired
    MailRepositoryImpl mailRepositoryImpl;

    @Autowired
    EmployeeRepository employeeRepository;

    @Test
    @DisplayName("보낸 메일 조회")
    void findSendMail() {
        PageRequest pageRequest = PageRequest.of(0, 10);
        mailRepository.findMailboxesWithReceiveTypeBySend(29, pageRequest);
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

    */
/*@Test
    @DisplayName("메일 조회 (TRASH, IMPORT)")
    void findMailTrashAndImport() {
        MailTransDTO mailTransDTO = new MailTransDTO();
        mailTransDTO.setReceiveType("TRASH");
        mailTransDTO.setReceiveEmpId(29);
        PageRequest page = PageRequest.of(0, 5);
        Page<MailTransDTO> results = mailRepositoryImpl.findByMailReceiveType(mailTransDTO, page);

        results.stream().toList().forEach(System.out::println);
    }*//*


    @Test
    @DisplayName("메일 단건 조회")
    void findByOne() {
        */
/*mailRepositoryImpl.findByOne(23);*//*

    }

    @Test
    @DisplayName("보낸 메일함의 수신자 + 참조자 조회되는 상세 페이지")
    void findByTOAndCC() {
        MailBoxDTO dto = new MailBoxDTO();
        dto.setId(23);
        List<MailBoxDTO> results =
                mailRepositoryImpl.findByOneV2toCC(25);


        List<String> toNames = results.stream()
                .map(MailBoxDTO::getEmpNameTO)
                .collect(Collectors.toList());

        String[] array = toNames.toArray(new String[0]);
    }

    @Test
    @DisplayName("보낸 메일함의 수신자 + 참조자 조회되는 상세 페이지2")
    void findByTOAndCCV2() {
        MailBoxDTO dto = new MailBoxDTO();
        dto.setId(23);
        List<MailBoxDTO> results =
                mailRepositoryImpl.findByOneV2toCC(25);

        List<String> collect = results.stream()
                .map(names -> names.getEmpNameTO() + names.getEmpEmailsTO())
                .collect(Collectors.toList());

        String[] array = collect.toArray(new String[0]);

        System.out.println("array = " + Arrays.toString(array));
    }

    @Test
    @DisplayName("참조자를 조회 (이름 + 메일)")
    void findByAndCCV2() {
        MailBoxDTO dto = new MailBoxDTO();
        dto.setId(23);
        List<MailBoxDTO> results =
                mailRepositoryImpl.findByOneV2toCC(25);

        List<String> collect = results.stream()
                .map(names -> names.getEmpNameCC() + names.getEmpEmailsCC())
                .collect(Collectors.toList());

        String[] array = collect.toArray(new String[0]);
        System.out.println("Arrays.toString(array) = " + Arrays.toString(array));
    }
}


*/
