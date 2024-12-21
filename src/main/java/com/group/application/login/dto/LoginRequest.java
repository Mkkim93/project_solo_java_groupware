package com.group.application.login.dto;

import lombok.Data;

@Data
public class LoginRequest {

    private String empEmail;
    private String empPass;
    private String role;

    public LoginRequest(String empEmail, String empPass, String role) {
        this.empEmail = empEmail;
        this.empPass = empPass;
        this.role = role;
    }
}
