package com.group.web.admin.api;

import com.group.application.admin.dto.RegisterDTO;
import com.group.application.admin.service.AdminService;
import com.group.exception.DuplicateEntityException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AdminApiController {

    private AdminService adminService;

    /**
     * 사원 이메일 중복 체크
     */
    @PostMapping("/api/auth/check-email")
    public ResponseEntity<?> authCheckEmail(@RequestParam(value = "empEmail", required = true) String empEmail) {
        try {
            adminService.duplicateEmail(empEmail);
            return ResponseEntity.ok().body("사용 가능한 이메일 입니다.");
        } catch (DuplicateEntityException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/api/register")
    public ResponseEntity<?> registerForm(@ModelAttribute("registerDto") RegisterDTO registerDto) {
        return ResponseEntity.ok(registerDto);
    }
}
