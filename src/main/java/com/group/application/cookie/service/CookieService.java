package com.group.application.cookie.service;

import com.group.jwt.JwtUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CookieService {

    private final JwtUtil jwtUtil;

    // 현재 로그인한 사용자의 jwt 토큰 정보의 쿠기를 외부 호출
    public String getJwtFromCookies(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("jwtToken".equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    public String getEmpEmailFromCookies(HttpServletRequest request) {
        String token = getJwtFromCookies(request);
        return jwtUtil.getUsername(token);
    }

    public String getEmpUUIDFromCookies(HttpServletRequest request) {
        String token = getJwtFromCookies(request);
        return jwtUtil.getEmpUUID(token);
    }
}
