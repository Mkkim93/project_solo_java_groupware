package com.group.web.hr.controller.api;

import com.group.application.hr.dto.EmployeeDTO;
import com.group.application.hr.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HomeApiController {

    private final EmployeeService employeeService;

    @GetMapping("hr/api/sidebar")
    public ResponseEntity<EmployeeDTO> sidebar(@CookieValue("uuid") String empUUID, EmployeeDTO employeeDto) {
        employeeDto.setEmpUUID(empUUID);
        EmployeeDTO empResult = employeeService.findByEmployee(employeeDto);
        return ResponseEntity.ok(empResult);
    }
}
