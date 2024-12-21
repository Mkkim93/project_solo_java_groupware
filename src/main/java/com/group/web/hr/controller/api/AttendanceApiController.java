package com.group.web.hr.controller.api;

import com.group.application.hr.dto.AttendanceDTO;
import com.group.application.hr.dto.EmployeeDTO;
import com.group.application.hr.service.AttendanceService;
import com.group.application.hr.service.EmployeeService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("att")
@RequiredArgsConstructor
public class AttendanceApiController {

    /* TODO 현재 전체적인 로직은 jwt token 과 쿠키 세션방식으로 사용자 인증을 진행하면서 사용자의 데이터를 uuid 조건으로 호출하고 있다
    TODO 해결해야할 문제 : 일반 controller 에서 모델 객체로 데이터를 넘길 때 문제 없으나 비동기(ajax) 에서 바인딩 안됨
    TODO
     */

    private final EmployeeService employeeService;
    private final AttendanceService attendanceService;

    @PostMapping("/in")
    public ResponseEntity<?> workIn(@ModelAttribute AttendanceDTO attendancedto,
                                 EmployeeDTO employeeDto, @CookieValue("uuid") String empUUID,
                                 HttpServletRequest request) {

        employeeDto.setEmpUUID(empUUID);
        EmployeeDTO findDto = employeeService.findByAll(employeeDto);
        attendancedto.setEmployee(findDto.getId());
        AttendanceDTO result = attendanceService.workIn(attendancedto);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/out")
    public ResponseEntity<?> workOut(@ModelAttribute AttendanceDTO attendancedto,
                                  EmployeeDTO employeeDto, @CookieValue("uuid") String empUUID) {
        employeeDto.setEmpUUID(empUUID);
        EmployeeDTO findDto = employeeService.findByAll(employeeDto);
        attendancedto.setEmployee(findDto.getId());
        AttendanceDTO result = attendanceService.workOut(attendancedto);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
