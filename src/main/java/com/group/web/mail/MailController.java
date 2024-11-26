package com.group.web.mail;

import com.group.application.hr.dto.EmployeeDTO;
import com.group.application.hr.service.EmployeeService;
import com.group.application.mail.dto.MailBoxDTO;
import com.group.application.mail.service.MailService;
import com.group.domain.mail.entity.MailBox;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/mail")
@RequiredArgsConstructor
public class MailController {

    private final MailService mailService;
    private final EmployeeService employeeService;

    @GetMapping("/list")
    public String sendMailBox(Model model, EmployeeDTO employeeDTO) {

        List<MailBoxDTO> mailBoxList = mailService.findAllSendMailBox(employeeDTO);
        model.addAttribute("mailBoxList", mailBoxList);
        return "/mail/list";
    }

    @GetMapping("/write")
    public String writeMail(Model model, MailBoxDTO mailBoxDTO) {
        model.addAttribute("mailBoxDTO", mailService.findByAllDTO(mailBoxDTO));
        return "/mail/write";
    }

    @PostMapping("/writeProc")
    public String writeProc(Model model, MailBoxDTO mailBoxDTO) {
        model.addAttribute("mailBoxDTO", mailService.mailWrite(mailBoxDTO));
        return "redirect:/mail/list";
    }

    @GetMapping("/tome")
    public String toMeWriteMail(Model model, MailBoxDTO mailBoxDTO) {
        model.addAttribute("mailBoxDTO", mailService.findByAllDTO(mailBoxDTO));
        mailBoxDTO.setSenderEmployeeId(1); // TODO jwt
        String myEmpMail = mailService.findByEmpMail(mailBoxDTO.getSenderEmployeeId());
        model.addAttribute("empMail", myEmpMail);
        return "/mail/tome";
    }

    @PostMapping("/tomeProc")
    public String tomeProc(Model model, MailBoxDTO mailBoxDTO) {
        mailBoxDTO.setSenderEmployeeId(1); // TODO jwt
        String myEmpMail = mailService.findByEmpMail(mailBoxDTO.getSenderEmployeeId());
        model.addAttribute("empMail", myEmpMail);
        model.addAttribute("mailBoxDTO", mailService.mailWrite(mailBoxDTO));
        return "redirect:/mail/list";
    }
}
