package com.group.web.login.controller;

import com.group.application.hr.dto.EmployeeDTO;
import com.group.application.login.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequiredArgsConstructor
public class LoginController {

    private final static String LOGIN_PATH = "/login";
    private final LoginService loginService;

    @GetMapping(LOGIN_PATH)
    public String login(Model model) {
        model.addAttribute("employeeDTO", new EmployeeDTO());
        return LOGIN_PATH;
    }

    @PostMapping(LOGIN_PATH)
    public String home(@RequestParam("empEmail") String empEmail,
                       @RequestParam("empPass") String empPass,
                       Model model) {
        model.addAttribute("employeeResult",
                loginService.findByEmpInfo(empEmail, empPass));
        return "redirect:/hr/home";
    }
}
