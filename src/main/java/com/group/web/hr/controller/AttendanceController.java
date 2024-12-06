package com.group.web.hr.controller;

import com.group.application.cookie.service.CookieService;
import com.group.application.hr.dto.AttendanceDTO;
import com.group.application.hr.dto.EmployeeDTO;
import com.group.application.hr.service.AttendanceService;
import com.group.application.hr.service.EmployeeService;
import jakarta.servlet.http.HttpServletRequest;
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
    private final CookieService cookieService;

    @GetMapping("/atten")
    public String detail(HttpServletRequest request, Model model,
                         AttendanceDTO attendancedto, EmployeeDTO employeedto){

        attendancedto.setAttDate(LocalDate.now());

        String uuid = cookieService.getEmpUUIDFromCookies(request);
        employeedto.setEmpUUID(uuid);

        EmployeeDTO findDto = employeeService.findByAll(employeedto);
        attendancedto.setEmployee(findDto.getId());

        model.addAttribute("weekWorkTime", attendanceService.findByWeekOfMonthLogic(attendancedto));
        model.addAttribute("remainWorkTime", attendanceService.remainingWorkHoursToWeek(attendancedto));
        model.addAttribute("monthWorkTime", attendanceService.findByMonthDurationSum(attendancedto));
        model.addAttribute("employeeDto", findDto);

        return "/hr/atten";
    }
}
