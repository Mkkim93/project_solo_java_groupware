package com.group.web.mail;

import com.group.application.hr.dto.EmployeeDTO;
import com.group.application.mail.dto.MailBoxDTO;
import com.group.application.mail.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/mail")
@RequiredArgsConstructor
public class MailController {

    private final MailService mailService;

    @GetMapping("/list")
    public String sendMailBox(Model model, EmployeeDTO employeeDTO) {

        List<MailBoxDTO> mailBoxList = mailService.findAllSendMailBox(employeeDTO);
        model.addAttribute("mailBoxList", mailBoxList);
        return "/mail/list";
    }
}
