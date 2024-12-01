package com.group.web.hr.controller;

import com.group.application.hr.dto.EmployeeDTO;
import com.group.application.hr.service.AttendanceService;
import com.group.application.hr.service.EmployeeService;
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

    @GetMapping("/home")
    public String home(Model model, EmployeeDTO employeeDto) {
        // TODO 인증된 사용자 정보 사용
        employeeDto.setId(1);
        model.addAttribute("employeeDto", employeeService.findByAll(employeeDto));
        model.addAttribute("departmentDto", employeeService.findByIdDepartInfo(employeeDto));
        model.addAttribute("attendanceDto", attendanceService.findByIdAttInfo(employeeDto));
        return "/hr/home";
    }

    @GetMapping("/detail")
    public String detail(Model model, EmployeeDTO employeeDto) {
        employeeDto.setId(1);
        model.addAttribute("employeeDto", employeeService.findByAll(employeeDto));
        return "/hr/detail";
    }

    @GetMapping("/modify/{id}")
    public String modify(@PathVariable("id") Integer id, EmployeeDTO employeeDto, Model model) {
        employeeDto.setId(id);
        model.addAttribute("employeeDto", employeeService.findByAll(employeeDto));
        return "/hr/modify";
    }

    @PostMapping("/modify/update/{id}")
    public String modifyProc(@PathVariable("id") Integer id,
                              @ModelAttribute EmployeeDTO employeeDto) {
        employeeDto.setId(id);
        employeeService.updateProfile(employeeDto);
        return "redirect:/hr/detail";
    }
}
