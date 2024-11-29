package com.group.web.login.controller;

import com.group.application.hr.dto.EmployeeDTO;
import com.group.application.login.service.JoinService;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequiredArgsConstructor
public class JoinController {

    public static final String REGISTER_PATH = "/register";
    private final JoinService joinService;

    @GetMapping(REGISTER_PATH)
    public String view(Model model) {
        model.addAttribute("employeeDTO", new EmployeeDTO());
        return REGISTER_PATH;
    }

    @PostMapping(REGISTER_PATH)
    public String proc(@ModelAttribute("employeeDTO") EmployeeDTO employeeDto) {
        joinService.save(employeeDto);
        return "redirect:/login";
    }
}
