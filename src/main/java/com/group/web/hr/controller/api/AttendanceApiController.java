package com.group.web.hr.controller.api;

import com.group.application.hr.dto.AttendanceDTO;
import com.group.application.hr.dto.EmployeeDTO;
import com.group.application.hr.service.AttendanceService;
import com.group.application.hr.service.EmployeeService;
import com.group.domain.hr.entity.Attendance;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/att")
@RequiredArgsConstructor
public class AttendanceApiController {

    private final AttendanceService attendanceService;

    @PostMapping("/in")
    public ResponseEntity workIn(@ModelAttribute AttendanceDTO attendancedto) {
        attendancedto.setEmployee(1);
        AttendanceDTO result = attendanceService.workIn(attendancedto);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/out")
    public ResponseEntity workOut(@ModelAttribute AttendanceDTO attendancedto) {
        attendancedto.setEmployee(1);
        AttendanceDTO result = attendanceService.workOut(attendancedto);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
