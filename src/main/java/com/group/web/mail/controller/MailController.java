package com.group.web.mail.controller;

import com.group.application.hr.dto.EmployeeDTO;
import com.group.application.hr.service.EmployeeService;
import com.group.application.mail.dto.MailBoxDTO;
import com.group.application.mail.service.MailService;
import com.group.domain.mail.entity.enums.MailStatus;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/mail")
@RequiredArgsConstructor
public class MailController {

    private final MailService mailService;
    private final EmployeeService employeeService;

    @GetMapping("/list")
    public String sendMailBox(Model model, MailBoxDTO mailBoxDto, @CookieValue(value = "uuid") String empUUID, EmployeeDTO employeeDto,
                              @RequestParam(value = "size", defaultValue = "15") int size,
                              @RequestParam(value = "page", defaultValue = "0") int page) {

        PageRequest pageRequest = PageRequest.of(page, size);
        employeeDto.setEmpUUID(empUUID);
        EmployeeDTO dto = employeeService.findByEmployee(employeeDto);
        mailBoxDto.setSenderEmployeeId(dto.getId());

        model.addAttribute("employeeDto", dto);
        model.addAttribute("myMailBoxDto", mailService.findByMyMailBox(mailBoxDto, pageRequest));

        return "/mail/list";
    }

    @GetMapping("/write")
    public String writeMail(Model model, @CookieValue("uuid") String empUUID, EmployeeDTO employeeDto) {
        employeeDto.setEmpUUID(empUUID);
        model.addAttribute("employeeDto", employeeService.findByEmployee(employeeDto));
        model.addAttribute("mailBoxDto", new MailBoxDTO());
        return "/mail/write";
    }

    @PostMapping("/writeProc")
    public String writeProc(@RequestParam("action") String actionBtn,
                            @ModelAttribute MailBoxDTO mailBoxDto, EmployeeDTO employeeDto,
                            @CookieValue(value = "uuid") String empUUID) {
        employeeDto.setEmpUUID(empUUID);
        EmployeeDTO dto = employeeService.findByEmployee(employeeDto);
        mailBoxDto.setSenderEmployeeId(dto.getId());
        mailBoxDto.setMailStatus(MailStatus.valueOf(actionBtn));
        log.info("actionBtn", actionBtn);
        mailService.sendMailToRecipient(mailBoxDto);

        return "redirect:/mail/list";
    }

    @GetMapping("/tome")
    public String toMeWriteMail(@CookieValue(value = "uuid") String empUUID, EmployeeDTO employeeDto,
                                Model model, MailBoxDTO mailBoxDTO) {
        model.addAttribute("mailBoxDto", new MailBoxDTO());
        employeeDto.setEmpUUID(empUUID);
        EmployeeDTO dto = employeeService.findByEmployee(employeeDto);
        mailBoxDTO.setSenderEmployeeId(dto.getId());
        String myEmpMail = mailService.findByEmpMail(mailBoxDTO.getSenderEmployeeId());
        model.addAttribute("empMail", myEmpMail);
        return "/mail/tome";
    }

    @PostMapping("/tomeProc")
    public String tomeProc(EmployeeDTO employeeDto,
                           @CookieValue(value = "uuid") String empUUID,
                           Model model, MailBoxDTO mailBoxDTO) {
        employeeDto.setEmpUUID(empUUID);
        EmployeeDTO dto = employeeService.findByEmployee(employeeDto);
        mailBoxDTO.setSenderEmployeeId(dto.getId());
        String myEmpMail = mailService.findByEmpMail(mailBoxDTO.getSenderEmployeeId());
        model.addAttribute("empMail", myEmpMail);
        model.addAttribute("mailBoxDto", mailService.sendMailTome(mailBoxDTO));
        return "redirect:/mail/list";
    }

    @GetMapping("/detail")
    public String detail(@RequestParam("id") Integer id, Model model) {
        model.addAttribute("mailBoxDto", mailService.detail(id));
        return "/mail/detail";
    }
}
