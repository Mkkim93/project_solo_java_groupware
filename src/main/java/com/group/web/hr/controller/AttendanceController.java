package com.group.web.hr.controller;

import com.group.application.hr.dto.AttendanceDTO;
import com.group.application.hr.service.AttendanceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/hr")
public class AttendanceController {

    private final AttendanceService attendanceService;

    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    @GetMapping("/detail")
    public String detail(Model model, AttendanceDTO attendanceDTO) {
        model.addAttribute("attendanceDTO", attendanceService.findAllByEmpAttInfo(attendanceDTO, attendanceDTO.getAttDate()));
        return "/hr/detail";
    }
}
