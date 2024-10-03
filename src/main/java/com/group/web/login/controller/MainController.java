package com.group.web.login.controller;

import com.group.application.hr.dto.EmployeeDTO;
import com.group.application.hr.service.EmployeeService;
import com.group.application.login.dto.CustomUserDetails;
import com.group.domain.hr.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

@Controller
@ResponseBody
public class MainController {

    @GetMapping("/")
    public String mainP() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication.getPrincipal() instanceof CustomUserDetails) {
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

            // Employee 정보 가져오기
            String empEmail = userDetails.getUsername();
            // 권한(ROLE) 가져오기
            Integer id = userDetails.getEmployee().getId();
            Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
            String role = authorities.iterator().next().getAuthority();

            return "Main Controller : " + empEmail + ", ID: " + id + ", roleType : " + role;
        }

        return "Unauthorized";
    }
}
