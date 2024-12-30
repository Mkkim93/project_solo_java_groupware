package com.group.web.mail.controller.api;

import com.group.application.hr.dto.EmployeeDTO;
import com.group.application.hr.service.EmployeeService;
import com.group.application.mail.dto.MailBoxDTO;
import com.group.application.mail.dto.MailReplyDTO;
import com.group.application.mail.dto.MailTransDTO;
import com.group.application.mail.service.MailService;
import com.group.application.mail.service.MailTransService;
import com.group.application.mailfile.dto.MailFileDTO;
import com.group.application.mailfile.service.MailFileStoreService;
import com.group.domain.mail.entity.enums.MailStatus;
import com.group.domain.mailfile.entity.MailFileStore;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MailApiController {

    private final MailService mailService;
    private final MailFileStoreService mailFileStoreService;
    private final EmployeeService employeeService;
    private final MailTransService mailTransService;

    @GetMapping("/api/folder1/{mailType}")
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
    public ResponseEntity<Page<MailBoxDTO>> findByMyMailStatusTypeBox(@ModelAttribute MailBoxDTO mailBoxDto,
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
            Page<MailBoxDTO> results = mailService.findReceiveTypeBySend(mailBoxDto, pageRequest); // mailboxId = null
            return ResponseEntity.ok(results);
        }


        if (mailStatus.equals("DRAFT")) {

            Page<MailBoxDTO> results = mailService.findReceiveTypeByDraft(mailBoxDto, pageRequest);
            return ResponseEntity.ok(results);
        }
        return ResponseEntity.badRequest().body(Page.empty());
    }

    @PostMapping("/api/uploadFiles")
    public ResponseEntity<List<MailFileDTO>> uploadMailFile(@RequestParam(value = "file", required = false) List<MultipartFile> mailFiles,
                                                            @RequestParam("mailBoxId") Integer mailBoxId) throws IOException {
        List<MailFileDTO> result = mailFileStoreService.save(mailBoxId, mailFiles);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/api/deleteFiles")
    public ResponseEntity<List<MailFileDTO>> deleteMailFile(@RequestParam(value = "file", required = false) List<MultipartFile> mailFiles
                                                            ) {

        return null;
    }

    @PostMapping("/api/receiveMail")
    public ResponseEntity<List<MailBoxDTO>> receiveMail() {

        return null;
    }
    /**
     * 메일 삭제 (메일함 -> 휴지통)
     * @return
     */
    @PostMapping("/api/trash")
    public void deleteMail(@RequestParam(value = "mailBoxId", required = false) Integer mailBoxId) {
        mailTransService.mailTransByTrash(mailBoxId);
    }

    @PostMapping("/api/delete")
    public void delete(@RequestParam(value = "mailBoxId", required = false) Integer mailBoxId) {
        mailTransService.deleteMail(mailBoxId);
    }

    @PostMapping("/api/checked/delete")
    public void checkedTrash(@RequestParam(value = "mailBoxIds", required = false) List<Integer> mailBoxIds,
                                          HttpServletResponse response) throws IOException {
        mailTransService.checkBoxTrashMailBoxId(mailBoxIds);
        response.sendRedirect("/mail/list");
    }

    @PostMapping("/api/reply")
    public void replySendMail(@RequestParam(value = "mailBoxId", required = false) Integer mailBoxId,
                              @RequestParam(value = "file", required = false) List<MultipartFile> mailFiles,
                              @CookieValue("uuid") String empUUID, EmployeeDTO employeeDto,MailReplyDTO mailReplyDto,
                              HttpServletResponse response) throws IOException {

        employeeDto.setEmpUUID(empUUID);
        employeeService.findByEmployee(employeeDto);
        mailReplyDto.setMailParentId(mailBoxId);
        mailService.writeReplyMail(mailReplyDto, mailFiles);
        response.sendRedirect("/mail/list");
    }
}
