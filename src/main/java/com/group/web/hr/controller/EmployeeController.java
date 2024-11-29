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
    public String home(Model model, EmployeeDTO employeeDTO) {
        // TODO 인증된 사용자 정보 사용
        employeeDTO.setId(1);
        model.addAttribute("employeeDTO", employeeService.findByAll(employeeDTO));
        model.addAttribute("departmentDTO", employeeService.findByIdDepartInfo(employeeDTO));
        model.addAttribute("attendanceDTO", attendanceService.findByIdAttInfo(employeeDTO));
        return "/hr/home";
    }

    @GetMapping("/detail")
    public String detail(Model model, EmployeeDTO employeeDTO) {
        employeeDTO.setId(1);
        model.addAttribute("employeeDTO", employeeService.findByAll(employeeDTO));
        return "/hr/detail";
    }

    @GetMapping("/modify/{id}")
    public String modify(@PathVariable("id") Integer id, EmployeeDTO employeeDTO, Model model) {
        employeeDTO.setId(id);
        model.addAttribute("employeeDTO", employeeService.findByAll(employeeDTO));
        return "/hr/modify";
    }

    @PostMapping("/modify/update/{id}")
    public String modifyProc(@PathVariable("id") Integer id,
                              @ModelAttribute EmployeeDTO employeeDTO) {
        employeeDTO.setId(id);
        employeeService.updateProfile(employeeDTO);
        return "redirect:/hr/detail";
    }
}
