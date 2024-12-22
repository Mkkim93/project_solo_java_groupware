package com.group.web.mail.controller.api;

import com.group.application.hr.dto.EmployeeDTO;
import com.group.application.hr.service.EmployeeService;
import com.group.application.mail.dto.MailTransDTO;
import com.group.application.mail.service.MailService;
import com.group.application.mail.service.MailTransService;
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

    /*@GetMapping("/api/folder/{mailType}")
    public ResponseEntity<Page<MyMailBoxDTO>> findByMyMailTypeBox(MailBoxDTO mailBoxDto,
            @PathVariable("mailType") String mailType, EmployeeDTO employeeDto,
            @RequestParam(value = "size", defaultValue = "15") int size,
            @RequestParam(value = "page", defaultValue = "0") int page, @CookieValue(value = "uuid") String empUUID) {
        employeeDto.setEmpUUID(empUUID);
        EmployeeDTO dto = employeeService.findByEmployee(employeeDto);
        mailBoxDto.setSenderEmployeeId(dto.getId());
        mailBoxDto.setMailType(mailType);
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<MyMailBoxDTO> resultMailBox = mailService.searchByMailTypeList(mailBoxDto, pageRequest);

        return ResponseEntity.ok(resultMailBox);
    }*/

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
    public ResponseEntity<Page<MailTransDTO>> findByMyMailReceiveTypeBox(MailTransDTO mailTransDto,@PathVariable("receiveType") String receiveType, EmployeeDTO employeeDto,
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
    public ResponseEntity<Page<MailTransDTO>> findByMyMailStatusTypeBox(MailTransDTO mailTransDto,@PathVariable("mailStatus") String mailStatus,
                                                                        EmployeeDTO employeeDto,
                                                                         @RequestParam(value = "size", defaultValue = "15") int size,
                                                                         @RequestParam(value = "page", defaultValue = "0") int page,
                                                                         @CookieValue(value = "uuid") String empUUID) {
        employeeDto.setEmpUUID(empUUID);
        EmployeeDTO dto = employeeService.findByEmployee(employeeDto);
        mailTransDto.setReceiveEmpId(dto.getId());
        mailTransDto.setMailStatus(mailStatus);
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<MailTransDTO> results = mailTransService.findByMailStatus(mailTransDto, pageRequest);

        return ResponseEntity.ok(results);
    }
}
