package com.group.web.hr.controller;

import com.group.application.hr.dto.AttendanceDTO;
import com.group.application.hr.dto.EmployeeDTO;
import com.group.application.hr.service.AttendanceService;
import com.group.application.hr.service.EmployeeService;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/hr")
public class AttendanceController {

    private final AttendanceService attendanceService;
    private final EmployeeService employeeService;

    public AttendanceController(AttendanceService attendanceService,
                                EmployeeService employeeService) {
        this.attendanceService = attendanceService;
        this.employeeService = employeeService;
    }

    @GetMapping("/detail")
    public String detail(Model model, AttendanceDTO attendanceDTO, EmployeeDTO employeeDTO,
                         @RequestParam(value = "page", defaultValue = "0") Integer page,
                         @RequestParam(value = "size", defaultValue = "11") Integer size){
        PageRequest pageRequest = PageRequest.of(page, size);

        attendanceDTO.setAttDate(LocalDate.now());
        attendanceDTO.setEmployee(1); // TODO 임시 사원 ID 부여 나중에 jwt 토큰 처리

        // 임시 id test
        employeeDTO.setId(attendanceDTO.getEmployee()); // TODO jwt Token

        model.addAttribute("weekWorkTime", attendanceService.findByWeekOfMonthLogic(attendanceDTO));
        model.addAttribute("remainWorkTime", attendanceService.remainingWorkHoursToWeek(attendanceDTO));
        model.addAttribute("monthWorkTime", attendanceService.findByMonthDurationSum(attendanceDTO));
        model.addAttribute("employeeDTO", employeeService.findByAll(employeeDTO));
        return "hr/detail";
    }
}
