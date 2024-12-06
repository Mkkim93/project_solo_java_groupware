package com.group.web.hr.controller;

import com.group.application.cookie.service.CookieService;
import com.group.application.hr.dto.EmployeeDTO;
import com.group.application.hr.service.AttendanceService;
import com.group.application.hr.service.EmployeeService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/hr")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;
    private final AttendanceService attendanceService;
    private final CookieService cookieService;

    @GetMapping("/home")
    public String home(HttpServletRequest request, Model model, EmployeeDTO employeeDto) {
        // TODO 인증된 사용자 정보 사용
        String uuid = cookieService.getEmpUUIDFromCookies(request);
        employeeDto.setEmpUUID(uuid);
        model.addAttribute("employeeDto", employeeService.findByAll(employeeDto));
        model.addAttribute("departmentDto", employeeService.findByIdDepartInfo(employeeDto));
        model.addAttribute("attendanceDto", attendanceService.findByIdAttInfo(employeeDto));
        return "/hr/home";
    }

    @GetMapping("/detail")
    public String detail(HttpServletRequest request, Model model, EmployeeDTO employeeDto) {
        String uuid = cookieService.getEmpUUIDFromCookies(request);
        employeeDto.setEmpUUID(uuid);
        model.addAttribute("employeeDto", employeeService.findByAll(employeeDto));
        return "/hr/detail";
    }

    @GetMapping("/modify/{id}")
    public String modify(Model model, EmployeeDTO employeeDto, HttpServletRequest request) {
        String uuid = cookieService.getEmpUUIDFromCookies(request);
        employeeDto.setEmpUUID(uuid);
        model.addAttribute("employeeDto", employeeService.findByAll(employeeDto));
        return "/hr/modify";
    }

    @PostMapping("/modify/update/{id}")
    public String modifyProc(@ModelAttribute EmployeeDTO employeeDto,
                             HttpServletRequest request) {
        String uuid = cookieService.getEmpUUIDFromCookies(request);
        employeeDto.setEmpUUID(uuid);
        employeeService.updateProfile(employeeDto);
        return "redirect:/hr/detail";
    }
}
