package com.group.application.mail.service;

import com.group.application.hr.dto.EmployeeDTO;
import com.group.application.mail.dto.MailBoxDTO;
import com.group.application.mail.dto.MyMailBoxDTO;
import com.group.domain.hr.entity.Employee;
import com.group.domain.hr.repository.EmployeeRepository;
import com.group.domain.mail.entity.MailBox;
import com.group.domain.mail.repository.MailQueryRepository;
import com.group.domain.mail.repository.MailRepository;
import com.group.domain.mail.repository.MailRepositoryImpl;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class MailService {

    private final MailRepository mailRepository;
    private final EmployeeRepository employeeRepository;
    private final MailRepositoryImpl mailRepositoryImpl;
    private final MailTransService mailTransService;
    private final MailQueryRepository mailQueryRepository;
    // 전체 메일함
    public Page<MyMailBoxDTO> findByMyMailBox(MailBoxDTO mailBoxDto, Pageable pageable) {
        return mailRepositoryImpl.findByMyMailBox(mailBoxDto, pageable);
    }

    public MailBoxDTO sendMailTome(MailBoxDTO mailBoxDto) {

        // TODO 여러 로직이 하나의 모듈에서 진행되고 있음
        // step1 : mailBox 에 데이터 저장
        MailBox mailBoxEntity = MailBox.builder()
                .id(mailBoxDto.getId())
                .mailTitle(mailBoxDto.getMailTitle())
                .mailContent(mailBoxDto.getMailContent())
                .mailDate(mailBoxDto.getSenderDate())
                .senderEmployee(Employee.builder()
                        .id(mailBoxDto.getSenderEmployeeId())
                        .build())
                .build();
        MailBox result = mailRepository.save(mailBoxEntity);

        // step2 : mailrecvstore 에 데이터 저장
        Integer empId = findByIdToMeMail(mailBoxDto.getReceiverEmail());

        mailRepository.saveReceiveStore(result.getId(), empId);

        mailTransService.save(result.getId(), empId);

        return mailBoxDto.toDTO(result);
    }

    public MailBoxDTO sendMailToRecipient(MailBoxDTO mailBoxDTO) {

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

        String receiverEmails = mailBoxDTO.getReceiverEmail();

        List<String> empEmails = Arrays.stream(receiverEmails.split(","))
                .map(String::trim)
                .collect(Collectors.toList());

        // step2 : mailrecvstore 에 데이터 저장
        List<Integer> byEmpId = findByEmpId(empEmails);

        mailQueryRepository.saveMailBoxIdAndReceiveId(result.getId(), byEmpId);

        // step3 : mailtrans 에 데이터 저장
        mailTransService.saveV2(result.getId(), byEmpId);

        return mailBoxDTO.toDTO(result);
    }

    /*
        메일 작성폼에서 받는 사원의 e-mail 을 입력하여 해당 사운의 id 를 조회하고
        id 를 mailrecvstore 에 해당 mailbox pk 와 저장
        email 을 리턴하는 것이 아니라 email 로 조회한 회원의 pk 를 리턴
     */
    public List<Integer> findByEmpId(List<String> empMail) {
        return employeeRepository.findByEmpEmails(empMail);
    }

    public Integer findByIdToMeMail(String empMail) {
        return employeeRepository.findByEmpEmail(empMail).getId();
    }

    public String findByEmpMail(Integer empId) {
        Employee employee = employeeRepository.findById(empId).get(); // TODO EXP
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.toDto(employee);
        return employeeDTO.getEmpEmail();
    }

    /**
     * 메일 상세 페이지
     */
    public MailBoxDTO detail(Integer id) {
        MailBox mailbox = mailRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("no id"));
        return mailRepositoryImpl.findByOne(mailbox.getId());
    }

    public Page<MyMailBoxDTO> searchByMailTypeList(MailBoxDTO mailBoxDto, Pageable pageable) {
        return mailRepositoryImpl.findByMailType(mailBoxDto, pageable);
    }
}
