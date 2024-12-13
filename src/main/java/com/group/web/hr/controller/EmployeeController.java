package com.group.web.hr.controller;

import com.group.application.cookie.service.CookieService;
import com.group.application.hr.dto.EmployeeDTO;
import com.group.application.hr.service.AttendanceService;
import com.group.application.hr.service.EmployeeService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
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
    public String home(@CookieValue("jwtToken") String token, Model model) {
        String uuid = cookieService.getEmpUUIDFromCookiesV2(token);
        EmployeeDTO dto = employeeService.findByEmployee(uuid);
        model.addAttribute("employeeDto", employeeService.findByAll(dto));
        model.addAttribute("departmentDto", employeeService.findByIdDepartInfo(dto));
        model.addAttribute("attendanceDto", attendanceService.findByIdAttInfo(dto));
        return "/hr/home";
    }

    @GetMapping("/detail")
    public String detail(@CookieValue("jwtToken") String token, Model model, EmployeeDTO employeeDto) {
        String uuid = cookieService.getEmpUUIDFromCookiesV2(token);
        EmployeeDTO dto = employeeService.findByEmployee(uuid);
        employeeDto.setEmpUUID(uuid);
        model.addAttribute("employeeDto", employeeService.findByAll(dto));
        return "/hr/detail";
    }

    @GetMapping("/modify/{id}")
    public String modify(Model model, EmployeeDTO employeeDto, @CookieValue("jwtToken") String token) {
        String uuid = cookieService.getEmpUUIDFromCookiesV2(token);
        EmployeeDTO dto = employeeService.findByEmployee(uuid);
        model.addAttribute("employeeDto", employeeService.findByAll(dto));
        return "/hr/modify";
    }

    @PostMapping("/modify/update/{id}")
    public String modifyProc(@ModelAttribute EmployeeDTO employeeDto,
                             @CookieValue("jwtToken") String token
                             ) {
        String uuid = cookieService.getEmpUUIDFromCookiesV2(token);
        EmployeeDTO dto = employeeService.findByEmployee(uuid);
        employeeDto.setEmpUUID(dto.getEmpUUID());
        employeeService.updateProfile(employeeDto);
        return "redirect:/hr/detail";
    }
}
