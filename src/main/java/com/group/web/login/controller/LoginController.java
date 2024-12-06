package com.group.web.login.controller;

import com.group.application.cookie.service.CookieService;
import com.group.application.hr.dto.EmployeeDTO;
import com.group.application.hr.service.EmployeeService;
import com.group.application.login.service.LoginService;
import com.group.domain.hr.repository.EmployeeRepository;
import com.group.jwt.JwtUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {

    private final static String LOGIN_PATH = "/login";
    private final LoginService loginService;

    @GetMapping(LOGIN_PATH)
    public String login() {
        return LOGIN_PATH;
    }

    @PostMapping(LOGIN_PATH)
    public String loginProc(EmployeeDTO dto, Model model) {
        model.addAttribute("employeeDto", loginService.login(dto));
        return "redirect:/hr/home";
    }
}

