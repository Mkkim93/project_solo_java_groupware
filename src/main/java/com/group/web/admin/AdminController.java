package com.group.web.admin;

import com.group.application.admin.dto.RegisterDTO;
import com.group.application.admin.service.AdminService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    /**
     * 관리자 메인 페이지
     */
    @GetMapping("/main")
    public String mainPage() {
        return "/admin/main";
    }

    /**
     * 사원 데이터 전송
     */
    @PostMapping("/registerProc")
    public String empRegistrationProc(@ModelAttribute(value = "RegisterDto") RegisterDTO registerDto) {
        adminService.registration(registerDto);
        return "redirect:/admin/main";
    }
}
