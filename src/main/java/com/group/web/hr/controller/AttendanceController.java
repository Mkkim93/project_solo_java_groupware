package com.group.web.hr.controller;

import com.group.application.hr.dto.AttendanceDTO;
import com.group.application.hr.dto.EmployeeDTO;
import com.group.application.hr.service.AttendanceService;
import com.group.application.hr.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/hr")
@RequiredArgsConstructor
public class AttendanceController {

    private final AttendanceService attendanceService;
    private final EmployeeService employeeService;

    @GetMapping("/atten")
    public String detail(Model model, AttendanceDTO attendancedto, EmployeeDTO employeedto,
                         @RequestParam(value = "page", defaultValue = "0") Integer page,
                         @RequestParam(value = "size", defaultValue = "11") Integer size){
        PageRequest pageRequest = PageRequest.of(page, size);

        attendancedto.setAttDate(LocalDate.now());
        attendancedto.setEmployee(1); // TODO 임시 사원 ID 부여 나중에 jwt 토큰 처리

        // 임시 id test
        employeedto.setId(attendancedto.getEmployee()); // TODO jwt Token

        model.addAttribute("weekWorkTime", attendanceService.findByWeekOfMonthLogic(attendancedto));
        model.addAttribute("remainWorkTime", attendanceService.remainingWorkHoursToWeek(attendancedto));
        model.addAttribute("monthWorkTime", attendanceService.findByMonthDurationSum(attendancedto));
        model.addAttribute("employeeDto", employeeService.findByAll(employeedto));

        return "/hr/atten";
    }
}
