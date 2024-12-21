package com.group.web.hr.controller;

import com.group.application.hr.dto.AttendanceDTO;
import com.group.application.hr.dto.EmployeeDTO;
import com.group.application.hr.service.AttendanceService;
import com.group.application.hr.service.EmployeeService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
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
    public String detail(Model model, @CookieValue(value = "uuid") String empUUID,
                         AttendanceDTO attendancedto, EmployeeDTO employeedto){
        employeedto.setEmpUUID(empUUID);
        attendancedto.setAttDate(LocalDate.now());

        EmployeeDTO findDto = employeeService.findByAll(employeedto);
        attendancedto.setEmployee(findDto.getId());

        model.addAttribute("weekWorkTime", attendanceService.findByWeekOfMonthLogic(attendancedto));
        model.addAttribute("remainWorkTime", attendanceService.remainingWorkHoursToWeek(attendancedto));
        model.addAttribute("monthWorkTime", attendanceService.findByMonthDurationSum(attendancedto));
        model.addAttribute("employeeDto", findDto);

        return "/hr/atten";
    }
}
