package com.group.web.mail.controller.api;

import com.group.application.hr.dto.EmployeeDTO;
import com.group.application.hr.service.EmployeeService;
import com.group.application.mail.dto.MailBoxDTO;
import com.group.application.mail.dto.MailTransDTO;
import com.group.application.mail.service.MailService;
import com.group.application.mail.service.MailTransService;
import com.group.domain.mail.entity.enums.MailStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MailApiController {

    private final MailService mailService;
    private final EmployeeService employeeService;
    private final MailTransService mailTransService;

    @GetMapping("/api/folder/{mailType}")
    public ResponseEntity<Page<MailTransDTO>> findByMyMailTypeBox(MailTransDTO mailTransDto,
                                                                  @PathVariable("mailType") String mailType, EmployeeDTO employeeDto,
                                                                  @RequestParam(value = "size", defaultValue = "15") int size,
                                                                  @RequestParam(value = "page", defaultValue = "0") int page,
                                                                  @CookieValue(value = "uuid") String empUUID) {
        employeeDto.setEmpUUID(empUUID);
        EmployeeDTO dto = employeeService.findByEmployee(employeeDto);
        mailTransDto.setReceiveEmpId(dto.getId());
        mailTransDto.setMailTypes(mailType);
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<MailTransDTO> results = mailTransService.typeByMailSearch(mailTransDto, pageRequest);

        return ResponseEntity.ok(results);
    }

    @GetMapping("/api/folder2/{receiveType}")
    public ResponseEntity<Page<MailTransDTO>> findByMyMailReceiveTypeBox(MailTransDTO mailTransDto,
                                                                         @PathVariable("receiveType") String receiveType, EmployeeDTO employeeDto,
                                                                         @RequestParam(value = "size", defaultValue = "15") int size,
                                                                         @RequestParam(value = "page", defaultValue = "0") int page,
                                                                         @CookieValue(value = "uuid") String empUUID) {
        employeeDto.setEmpUUID(empUUID);
        EmployeeDTO dto = employeeService.findByEmployee(employeeDto);
        mailTransDto.setReceiveEmpId(dto.getId());
        mailTransDto.setReceiveType(receiveType);
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<MailTransDTO> results = mailTransService.receiveTypeBySearch(mailTransDto, pageRequest);

        return ResponseEntity.ok(results);
    }

    @GetMapping("/api/folder3/{mailStatus}")
    public ResponseEntity<Page<MailBoxDTO>> findByMyMailStatusTypeBox(MailBoxDTO mailBoxDto,
                                                                        @PathVariable("mailStatus") String mailStatus, EmployeeDTO employeeDto,
                                                                        @RequestParam(value = "size", defaultValue = "15") int size,
                                                                        @RequestParam(value = "page", defaultValue = "0") int page,
                                                                        @CookieValue(value = "uuid") String empUUID) {
        employeeDto.setEmpUUID(empUUID);
        EmployeeDTO dto = employeeService.findByEmployee(employeeDto);
        mailBoxDto.setSenderEmployeeId(dto.getId());
        mailBoxDto.setMailStatus(MailStatus.valueOf(mailStatus));
        PageRequest pageRequest = PageRequest.of(page, size);

        if (mailStatus.equals("SENDED")) {
            Page<MailBoxDTO> results = mailService.findReceiveTypeBySend(mailBoxDto, pageRequest);
            return ResponseEntity.ok(results);
        }


        if (mailStatus.equals("DRAFT")) {

            Page<MailBoxDTO> results = mailService.findReceiveTypeByDraft(mailBoxDto, pageRequest);
            return ResponseEntity.ok(results);
        }
        return ResponseEntity.badRequest().body(Page.empty());
    }
}
