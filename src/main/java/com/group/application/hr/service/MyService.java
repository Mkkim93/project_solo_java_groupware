package com.group.application.hr.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class MyService {

    public void someServiceMethod() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String empEmail = authentication.getName(); // 현재 인증된 사용자의 이메일
        // 비즈니스 로직 수행
    }
}
