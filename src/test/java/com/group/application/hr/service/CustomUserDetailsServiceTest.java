package com.group.application.hr.service;

import com.group.jwt.JwtUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CustomUserDetailsServiceTest {

    @Autowired
    private CustomUserDetailsService service;

    @Autowired
    JwtUtil jwtUtil;

    @Test
    void test() {
        String empEmail = "king00314@naver.com";
        UserDetails userDetails = service.loadUserByUsername(empEmail);
        System.out.println("userDetails.getUsername() = " + userDetails.getUsername());
        System.out.println("userDetails.getPassword() = " + userDetails.getPassword());
        System.out.println("userDetails.getAuthorities().iterator().next().getAuthority() = " + userDetails.getAuthorities().iterator().next().getAuthority());


    }

}