package com.group.application.mail.service;

import com.group.application.hr.dto.EmployeeDTO;
import com.group.application.mail.dto.MailBoxDTO;
import com.group.domain.hr.entity.Employee;
import com.group.domain.mail.entity.MailBox;
import com.group.domain.mail.repository.MailRepository;
import com.group.domain.mail.repository.MailRepositoryImpl;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MailService {

    private final MailRepository mailRepository;

    public List<MailBoxDTO> findAllSendMailBox(EmployeeDTO employeeDTO) {

        employeeDTO.setId(2);
        List<Object[]> receivedMails = mailRepository.findReceivedMails(employeeDTO.getId());
       return receivedMails.stream()
                .map(receivedMail -> new MailBoxDTO(
                        (String) receivedMail[0], // 메일 제목
                        (String) receivedMail[1], // 메일 내용
                        (Integer) receivedMail[2], // 보낸 사람 ID
                        (String) receivedMail[3],
                        ((Timestamp) receivedMail[4]).toLocalDateTime() // sql 의 TimeStamp 타입과 자바의 LocalDate 매핑
                ))
                .toList();
    }

    public MailBoxDTO mailWrite(MailBoxDTO mailBoxDTO) {
        MailBox mailBoxEntity = MailBox.builder()
                .id(mailBoxDTO.getId())
                .mailTitle(mailBoxDTO.getMailTitle())
                .mailContent(mailBoxDTO.getMailContent())
                .mailDate(mailBoxDTO.getSenderDate())
                .senderEmployee(Employee.builder()
                        .id(mailBoxDTO.getSenderEmployeeId())
                        .build())
                .build();
        MailBox result = mailRepository.save(mailBoxEntity);

        return mailBoxDTO.toDTO(result);
    }
}
