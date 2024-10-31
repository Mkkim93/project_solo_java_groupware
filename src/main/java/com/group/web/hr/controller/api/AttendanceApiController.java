package com.group.web.hr.controller.api;

import com.group.application.hr.dto.AttendanceDTO;
import com.group.application.hr.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/attendance")
public class AttendanceApiController {

    private final AttendanceService attendanceService;

    @Autowired
    public AttendanceApiController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    // 출근 처리 로직 ajax
    @PostMapping("/on")
    public ResponseEntity on(@ModelAttribute AttendanceDTO attendanceDTO) {
        attendanceDTO.setEmployee(1);
        AttendanceDTO attendanceDTOResult = attendanceService.attOn(attendanceDTO);
        return new ResponseEntity<>(attendanceDTOResult, HttpStatus.OK);
    }

    @PostMapping("/off")
    public ResponseEntity off(@ModelAttribute AttendanceDTO attendanceDTO) {
        attendanceDTO.setEmployee(1);
        AttendanceDTO attendanceDTOResult = attendanceService.attOff(attendanceDTO);
        return new ResponseEntity<>(attendanceDTOResult, HttpStatus.OK);
    }
}
