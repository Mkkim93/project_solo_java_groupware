package com.group.web.mail.controller;

import com.group.application.cookie.service.CookieService;
import com.group.application.hr.dto.EmployeeDTO;
import com.group.application.hr.service.EmployeeService;
import com.group.application.mail.dto.MailBoxDTO;
import com.group.application.mail.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/mail")
@RequiredArgsConstructor
public class MailController {

    private final MailService mailService;
    private final EmployeeService employeeService;
    private final CookieService cookieService;

    @GetMapping("/list")
    public String sendMailBox(@CookieValue("jwtToken") String token, Model model, MailBoxDTO mailBoxDto,
                              @RequestParam(value = "size", defaultValue = "15") int size,
                              @RequestParam(value = "page", defaultValue = "0") int page) {
        PageRequest pageRequest = PageRequest.of(page, size);
        String uuid = cookieService.getEmpUUIDFromCookiesV2(token);
        EmployeeDTO dto = employeeService.findByEmployee(uuid);
        mailBoxDto.setSenderEmployeeId(dto.getId());

        model.addAttribute("employeeDto", dto);
        model.addAttribute("myMailBoxDto", mailService.findByMyMailBox(mailBoxDto, pageRequest));
        return "/mail/list";
    }

    @GetMapping("/write")
    public String writeMail(@CookieValue("jwtToken") String token, Model model) {
        String uuid = cookieService.getEmpUUIDFromCookiesV2(token);
        EmployeeDTO dto = employeeService.findByEmployee(uuid);
        model.addAttribute("employeeDto", dto);
        model.addAttribute("mailBoxDto", new MailBoxDTO());
        return "/mail/write";
    }

    @PostMapping("/writeProc")
    public String writeProc(@CookieValue("jwtToken") String token, Model model, MailBoxDTO mailBoxDto) {
        String uuid = cookieService.getEmpUUIDFromCookiesV2(token);
        EmployeeDTO dto = employeeService.findByEmployee(uuid);
        mailBoxDto.setSenderEmployeeId(dto.getId());
        model.addAttribute("mailBoxDto", mailService.sendMailToRecipient(mailBoxDto));
        return "redirect:/mail/list";
    }

    @GetMapping("/sent")
    public String sent() {
        // TODO 보낸 메일함
        return "/mail/sent";
    }

    @GetMapping("/tome")
    public String toMeWriteMail(@CookieValue("jwtToken") String token, Model model, MailBoxDTO mailBoxDTO) {
        model.addAttribute("mailBoxDto", new MailBoxDTO());
        String uuid = cookieService.getEmpUUIDFromCookiesV2(token);
        EmployeeDTO dto = employeeService.findByEmployee(uuid);
        mailBoxDTO.setSenderEmployeeId(dto.getId());
        String myEmpMail = mailService.findByEmpMail(mailBoxDTO.getSenderEmployeeId());
        model.addAttribute("empMail", myEmpMail);
        return "/mail/tome";
    }

    @PostMapping("/tomeProc")
    public String tomeProc(@CookieValue("jwtToken") String token, Model model, MailBoxDTO mailBoxDTO) {
        String uuid = cookieService.getEmpUUIDFromCookiesV2(token);
        EmployeeDTO dto = employeeService.findByEmployee(uuid);
        mailBoxDTO.setSenderEmployeeId(dto.getId());
        String myEmpMail = mailService.findByEmpMail(mailBoxDTO.getSenderEmployeeId());
        model.addAttribute("empMail", myEmpMail);
        model.addAttribute("mailBoxDto", mailService.sendMailTome(mailBoxDTO));
        return "redirect:/mail/list";
    }

    /*@GetMapping("/detail")*/
    public String detail(@RequestParam("id") Integer id, Model model) {
        model.addAttribute("mailBoxDto", mailService.detail(id));
        return "/mail/detail";
    }
}
