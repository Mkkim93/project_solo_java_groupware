package com.group.web.mail.controller.api;

import com.group.application.cookie.service.CookieService;
import com.group.application.hr.dto.EmployeeDTO;
import com.group.application.hr.service.EmployeeService;
import com.group.application.mail.dto.MailBoxDTO;
import com.group.application.mail.service.DynamicMailService;
import com.group.application.mail.service.MailService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.query.sql.internal.ParameterRecognizerImpl;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/mail/list")
@RequiredArgsConstructor
public class MailApiController {

    private final MailService mailService;
    private final CookieService cookieService;
    private final EmployeeService employeeService;

    @GetMapping("/{MailType}")
    public ResponseEntity<Page<MailBoxDTO>> findAll(
            @CookieValue("jwtToken") String token,
            @PathVariable("mailType") String mailType,
            @RequestParam(value = "size", defaultValue = "15") int size,
            @RequestParam(value = "page", defaultValue = "0") int page) {

        String uuid = cookieService.getEmpUUIDFromCookiesV2(token);
        EmployeeDTO dto = employeeService.findByEmployee(uuid);

        return null;
    }
}
