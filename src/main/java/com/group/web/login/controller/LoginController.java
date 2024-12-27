package com.group.web.login.controller;

import com.group.application.cookie.CookieUtil;
import com.group.application.hr.dto.EmployeeDTO;
import com.group.application.hr.service.CustomUserDetailsService;
import com.group.application.login.service.LoginService;
import jakarta.servlet.http.Cookie;
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

    private final LoginService loginService;
    private final CookieUtil cookieUtil;

    @GetMapping("/login")
    public String login() {
        return "/login";
    }

    @PostMapping("/loginProc")
    public String loginProc(@ModelAttribute("employeeDto") EmployeeDTO employeeDto,
                            HttpServletResponse response) {
        loginService.login(employeeDto);
        response.addCookie(cookieUtil.saveCookie(employeeDto));
        return "redirect:/hr/home";
    }

    /*@GetMapping("/login")
    public String login(@ModelAttribute("employeeDto") EmployeeDTO employeeDto) {
        return "/login";
    }

    @PostMapping("/loginProc")
    public String loginProc(Model model, EmployeeDTO employeeDto, HttpServletResponse response) {
        EmployeeDTO dto = loginService.login(employeeDto);
        model.addAttribute("employeeDto", dto);
        Cookie cookie = cookieUtil.saveCookie(dto);
        response.addCookie(cookie);
        return "redirect:/hr/home";
    }*/
}

