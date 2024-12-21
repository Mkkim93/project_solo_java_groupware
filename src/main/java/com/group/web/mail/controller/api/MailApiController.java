package com.group.web.mail.controller.api;

import com.group.application.hr.dto.EmployeeDTO;
import com.group.application.hr.service.EmployeeService;
import com.group.application.mail.dto.MailBoxDTO;
import com.group.application.mail.dto.MyMailBoxDTO;
import com.group.application.mail.service.MailService;
import com.group.domain.mail.entity.enums.MailType;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MailApiController {

    private final MailService mailService;
    private final EmployeeService employeeService;

    @GetMapping("/api/folder/{mailType}")
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
    }
}
