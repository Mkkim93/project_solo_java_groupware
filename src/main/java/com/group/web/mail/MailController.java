package com.group.web.mail;

import com.group.application.hr.dto.EmployeeDTO;
import com.group.application.hr.service.EmployeeService;
import com.group.application.mail.dto.MailBoxDTO;
import com.group.application.mail.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/mail")
@RequiredArgsConstructor
public class MailController {

    private final MailService mailService;
    private final EmployeeService employeeService;

    @GetMapping("/list")
    public String sendMailBox(Model model, EmployeeDTO employeeDTO) {
        employeeDTO.setId(1); // TODO 임시 ID
        List<MailBoxDTO> mailBoxList = mailService.findAllBySendMail(employeeDTO);
        model.addAttribute("mailBoxList", mailBoxList);
        return "/mail/list";
    }

    @GetMapping("/write")
    public String writeMail(Model model) {
        model.addAttribute("mailBoxDto", new MailBoxDTO());
        return "/mail/write";
    }

    @PostMapping("/writeProc")
    public String writeProc(Model model, MailBoxDTO mailBoxDTO) {
        model.addAttribute("mailBoxDto", mailService.write(mailBoxDTO));
        return "redirect:/mail/list";
    }

    @GetMapping("/tome")
    public String toMeWriteMail(Model model, MailBoxDTO mailBoxDTO) {
        model.addAttribute("mailBoxDto", new MailBoxDTO());
        mailBoxDTO.setSenderEmployeeId(1); // TODO 임시 ID
        String myEmpMail = mailService.findByEmpMail(mailBoxDTO.getSenderEmployeeId());
        model.addAttribute("empMail", myEmpMail);
        return "/mail/tome";
    }

    @PostMapping("/tomeProc")
    public String tomeProc(Model model, MailBoxDTO mailBoxDTO) {
        mailBoxDTO.setSenderEmployeeId(1); // TODO 임시 ID
        String myEmpMail = mailService.findByEmpMail(mailBoxDTO.getSenderEmployeeId());
        model.addAttribute("empMail", myEmpMail);
        model.addAttribute("mailBoxDto", mailService.write(mailBoxDTO));
        return "redirect:/mail/list";
    }

    @GetMapping("/detail")
    public String detail(@RequestParam("id") Integer id, Model model) {
        model.addAttribute("mailBoxDto", mailService.detail(id));
        return "/mail/detail";
    }
}
