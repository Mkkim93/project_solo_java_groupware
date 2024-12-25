package com.group.application.mail.service;

import com.group.application.hr.dto.EmployeeDTO;
import com.group.application.mail.dto.MailBoxDTO;
import com.group.application.mail.dto.MyMailBoxDTO;
import com.group.application.mailfile.service.MailFileStoreService;
import com.group.domain.hr.entity.Employee;
import com.group.domain.hr.repository.EmployeeRepository;
import com.group.domain.mail.entity.MailBox;
import com.group.domain.mail.repository.MailQueryRepository;
import com.group.domain.mail.repository.MailRepository;
import com.group.domain.mail.repository.MailRepositoryImpl;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class MailService {

    private final MailFileStoreService mailFileStoreService;
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
                .mailDate(mailBoxDto.getMailDate())
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

    public void sendMailToRecipient(MailBoxDTO mailBoxDto, List<MultipartFile> file) {

        // logic 1 : mailBox 에 데이터 저장
        MailBox mailBoxEntity = MailBox.builder()
                .id(mailBoxDto.getId())
                .mailTitle(mailBoxDto.getMailTitle())
                .mailContent(mailBoxDto.getMailContent())
                .mailDate(mailBoxDto.getMailDate())
                .mailStatus(mailBoxDto.getMailStatus())
                .senderEmployee(Employee.builder()
                        .id(mailBoxDto.getSenderEmployeeId())
                        .build())
                .build();

        MailBox result = mailRepository.save(mailBoxEntity);

        Integer mailBoxId = result.getId();
        mailBoxDto.setId(mailBoxId);

        boolean existFile = file.stream().allMatch(MultipartFile::isEmpty);

        file.stream().toList().forEach(System.out::println);
        log.info("existFile", existFile);

        // 전송 메일이 파일 존재 여부 확인

        saveFile(mailBoxDto.getId(), file);


        String receiverEmails = mailBoxDto.getReceiverEmail();

        List<String> empEmails = Arrays.stream(receiverEmails.split(","))
                .map(String::trim)
                .collect(Collectors.toList());

        // logic 2 : mailrecvstore 에 데이터 저장
        List<Integer> byEmpId = findByEmpId(empEmails);
        mailQueryRepository.saveMailBoxIdAndReceiveId(result.getId(), byEmpId);

        // logic 3-1) 임시 저장
        if (mailBoxDto.getMailStatus().name().equals("DRAFT")) {
            return;
        }

        // logic 3-2) 최종 메일 발송
        if (mailBoxDto.getMailStatus().name().equals("SENDED")) {
            mailTransService.saveV2(result.getId(), byEmpId);
        }
    }

    // 메일을 전송할 때 메일 저장에 실패하면 예외를 던진다
    // 메일에 파일이 첨부되었을 때 파일 전송에 실패해도 메일을 전송 요청을 거부하도록 로직을 구성해야될듯
    private void saveFile(Integer mailBoxId, List<MultipartFile> file) {
        try {
            mailFileStoreService.save(mailBoxId, file);
        } catch (IOException e) {
            // TODO 예외처리 좀더 상세하게
            log.info("메일 전송/저장에 실패하였습니다.");
        }
    }

    /*
    //TODO Employee service 로 분리 직접 repository 에 접근하지 말것
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
                .orElseThrow(() -> new EntityNotFoundException("no mailBox id"));
            return mailRepositoryImpl.findByOne(mailbox.getId());
    }

    public Page<MailBoxDTO> findReceiveTypeBySend(MailBoxDTO mailBoxDto, Pageable pageable) {

        Page<Object[]> result = mailRepository.findMailboxesWithReceiveTypeBySend(mailBoxDto.getSenderEmployeeId(), pageable);

        return result.map(row -> new MailBoxDTO(
                (Integer) row[0],
                (String) row[1], // 메일 저장 시 여러명의 사용자의 메일 주소 또는 이름을 (,)로 구분하여 저장
                (Integer) row[2],
                (String) row[3],
                ((Timestamp) row[4]).toLocalDateTime()
        ));
    }

    public Page<MailBoxDTO> findReceiveTypeByDraft(MailBoxDTO mailBoxDto, Pageable pageable) {

        Page<Object[]> result = mailRepository.findMailBoxesWithReceiveTypeByDraft(mailBoxDto.getSenderEmployeeId(), pageable);

        return result.map(row -> new MailBoxDTO(
                (Integer) row[0],
                (String) row[1], // 메일 저장 시 여러명의 사용자의 메일 주소 또는 이름을 (,)로 구분하여 저장
                (Integer) row[2],
                (String) row[3],
                ((Timestamp) row[4]).toLocalDateTime()
        ));
    }
}
