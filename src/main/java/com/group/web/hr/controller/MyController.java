package com.group.web.hr.controller;

import com.group.application.login.dto.CustomUserDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class MyController {

    @GetMapping("/myPage")
    public ResponseEntity<String> protectedResource(Model model
            , @AuthenticationPrincipal CustomUserDetails userDetails) {
        // 인증된 사용자 정보 사용
        String empEmail = userDetails.getUsername();
        String empPass = userDetails.getPassword();
        model.addAttribute("empEmail", empEmail);
        model.addAttribute("empPass", empPass);
        return ResponseEntity.ok("Hello, " + empEmail);
    }
}
