package com.group.application.mail.service;

import com.group.application.hr.dto.EmployeeDTO;
import com.group.application.mail.dto.MailBoxDTO;
import com.group.domain.hr.entity.Employee;
import com.group.domain.hr.repository.EmployeeRepository;
import com.group.domain.mail.entity.MailBox;
import com.group.domain.mail.repository.MailRepository;
import com.group.domain.mail.repository.MailRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MailService {

    private final MailRepository mailRepository;
    private final EmployeeRepository employeeRepository;
    private final MailRepositoryImpl mailRepositoryImpl;

    public List<MailBoxDTO> findAllSendMailBox(EmployeeDTO employeeDTO) {

        // TODO jwt 구현 시 변경
        employeeDTO.setId(1);

        List<Object[]> receivedMails = mailRepository.findReceivedMails(employeeDTO.getId());
       return receivedMails.stream()
                .map(receivedMail -> new MailBoxDTO( // parameter 순서 맞춰서 바인딩
                        (String) receivedMail [0], // 메일 제목
                        (String) receivedMail [1], // 메일 내용
                        (Integer) receivedMail[2], // 보낸 사람 ID
                        (String) receivedMail [3],
                        ((Timestamp) receivedMail[4]).toLocalDateTime() // sql 의 TimeStamp 타입과 자바의 LocalDate 매핑
                ))
                .toList();
    }

    public MailBoxDTO mailWrite(MailBoxDTO mailBoxDTO) {

        // step1 : mailBox 에 데이터 저장
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

        // step2 : mailrecvstore 에 데이터 저장
        Integer empId = findByEmpMailToId(mailBoxDTO.getReceiverEmail());
        mailRepository.insertMailRecvStore(result.getId(), empId);

        return mailBoxDTO.toDTO(result);
    }

    /*
        메일 작성폼에서 받는 사원의 e-mail 을 입력하여 해당 사운의 id 를 조회하고
        id 를 mailrecvstore 에 해당 mailbox pk 와 저장
        email 을 리턴하는 것이 아니라 email 로 조회한 회원의 pk 를 리턴
     */
    public Integer findByEmpMailToId(String empMail) {
        return employeeRepository.findByEmpEmail(empMail).getId();
    }

    public String findByEmpMail(Integer empId) {
        Employee employee = employeeRepository.findById(empId).get();
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setDTO(employee);
        return employeeDTO.getEmpEmail();
    }

    public MailBoxDTO findByAllDTO(MailBoxDTO mailBoxDTO) {
        mailBoxDTO.setSenderEmployeeId(1); // TODO jwt 구현시 수정 현재 로그인된 id bind
        return mailRepositoryImpl.findByAll(mailBoxDTO);
    }



    public void toMeSaveMail(MailBoxDTO mailBoxDTO) {
        // TODO
    }
}
