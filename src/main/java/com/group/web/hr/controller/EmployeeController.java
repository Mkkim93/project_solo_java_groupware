package com.group.web.hr.controller;

import com.group.application.hr.dto.EmployeeDTO;
import com.group.application.hr.service.AttendanceService;
import com.group.application.hr.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/hr")
@RequiredArgsConstructor
public class EmployeeController {

    @Autowired
    private final EmployeeService employeeService;
    private final AttendanceService attendanceService;

    @GetMapping("/myPage")
    public String protectedResource(Model model, EmployeeDTO employeeDTO) {
        // TODO 인증된 사용자 정보 사용
        employeeDTO.setId(1);
        model.addAttribute("employeeDTO", employeeService.findByAll(employeeDTO));
        model.addAttribute("departmentDTO", employeeService.findByIdDepartInfo(employeeDTO));
        model.addAttribute("attendanceDTO", attendanceService.findByIdAttInfo(employeeDTO));
        return "/hr/myPage";
    }

    @GetMapping("/employeedetailview")
    public String myDetailView(Model model, EmployeeDTO employeeDTO) {
        employeeDTO.setId(1);
        model.addAttribute("employeeDTO", employeeService.findByAll(employeeDTO));
        return "/hr/employeedetailview";
    }

    @GetMapping("/employeemodify/{id}")
    public String updateView(@PathVariable("id") Integer id, EmployeeDTO employeeDTO, Model model) {
        employeeDTO.setId(id);
        model.addAttribute("employeeDTO", employeeService.findByAll(employeeDTO));
        return "/hr/employeemodify";
    }

    @PostMapping("/employeemodify/update/{id}")
    public String updateLogic(@PathVariable("id") Integer id,
                              @ModelAttribute EmployeeDTO employeeDTO) {
        employeeService.updateProfile(employeeDTO);
        return "redirect:/hr/employeedetailview";
    }
}
