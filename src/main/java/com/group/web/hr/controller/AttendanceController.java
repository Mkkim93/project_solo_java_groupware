package com.group.web.hr.controller;

import com.group.application.hr.dto.AttendanceDTO;
import com.group.application.hr.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;

@Controller
@RequestMapping("/attendance")
public class AttendanceController {

    private final AttendanceService attendanceService;

    @Autowired
    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    // 출근 처리 로직 ajax
    @PostMapping("/save")
    public ResponseEntity save(@ModelAttribute AttendanceDTO attendanceDTO) {
        attendanceDTO.setEmployee(1);
        AttendanceDTO attendanceDTOResult = attendanceService.attOn(attendanceDTO);
        return new ResponseEntity<>(attendanceDTOResult, HttpStatus.OK);
    }
}
