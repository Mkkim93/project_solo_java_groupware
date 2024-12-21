package com.group.web.hr.controller;

import com.group.application.hr.dto.EmployeeDTO;
import com.group.application.hr.service.AttendanceService;
import com.group.application.hr.service.EmployeeService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/hr")
@RequiredArgsConstructor
public class HomeController {

    private final EmployeeService employeeService;
    private final AttendanceService attendanceService;

    @GetMapping("/home")
    public String home(@CookieValue("uuid") String empUUID,  Model model, EmployeeDTO employeeDto) {
        employeeDto.setEmpUUID(empUUID);
        EmployeeDTO dto = employeeService.findByEmployee(employeeDto);
        model.addAttribute("employeeDto", dto);
        model.addAttribute("departmentDto", employeeService.findByIdDepartInfo(dto));
        model.addAttribute("attendanceDto", attendanceService.findByIdAttInfo(dto));
        return "/hr/home";
    }

    @GetMapping("/detail")
    public String detail(@CookieValue("uuid") String empUUID, Model model, EmployeeDTO employeeDto) {
        employeeDto.setEmpUUID(empUUID);
        model.addAttribute("employeeDto", employeeService.findByEmployee(employeeDto));
        return "/hr/detail";
    }

    @GetMapping("/modify/{id}")
    public String modify(@CookieValue("uuid") String empUUID, Model model, EmployeeDTO employeeDto) {
        employeeDto.setEmpUUID(empUUID);
        model.addAttribute("employeeDto", employeeService.findByEmployee(employeeDto));
        return "/hr/modify";
    }

    @PostMapping("/modify/update/{id}")
    public String modifyProc(@ModelAttribute EmployeeDTO employeeDto,
                             @CookieValue("uuid") String empUUID
                             ) {
        employeeDto.setEmpUUID(empUUID);
        employeeService.updateProfile(employeeDto);
        return "redirect:/hr/detail";
    }
}
