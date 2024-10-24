package com.group.web.login.controller;

import com.group.application.hr.dto.EmployeeDTO;
import com.group.application.hr.service.EmployeeService;
import com.group.application.jwt.JWTUtil;
import com.group.application.login.dto.CustomUserDetails;
import com.group.application.login.service.JoinService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Controller
public class JoinController {

    private final JoinService joinService;

    private final JWTUtil jwtUtil;
    private final EmployeeService employeeService;


    public JoinController(JoinService joinService,
                          JWTUtil jwtUtil,
                          EmployeeService employeeService) {
        this.employeeService = employeeService;
        this.joinService = joinService;
        this.jwtUtil = jwtUtil;
    }

    @GetMapping("/join")
    public String joinView(Model model, EmployeeDTO employeeDto) {
        model.addAttribute("employeeDto", employeeDto);  // 모델에 employeeDto를 추가
        log.info("welcome joinPage!");
        return "join";
    }

    @PostMapping("/join")
    public String joinProcess(@ModelAttribute("employeeDto") EmployeeDTO employeeDto) {
        joinService.joinProcess(employeeDto);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loinView(Model model, EmployeeDTO employeeDto) {
        model.addAttribute("employeeDto", employeeDto);
        log.info("welcome loginPage!");
        return "login";
    }

    @PostMapping("/login")
    public String loginProcess(Model model, EmployeeDTO employeeDto) {
        model.addAttribute("employeeDto", employeeDto);
        return "/board/boardlist";
    }
}
