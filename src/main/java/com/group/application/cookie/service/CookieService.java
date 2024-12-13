package com.group.application.cookie.service;

import com.group.jwt.JwtUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CookieService {

    private final JwtUtil jwtUtil;

    // 현재 로그인한 사용자의 jwt 토큰 정보의 쿠키를 외부로 호출
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

    public String getEmpUUIDFromCookies(HttpServletRequest request) {
        String token = getJwtFromCookies(request);
        return jwtUtil.getEmpUUID(token);
    }

    public String getEmpUUIDFromCookiesV2(String token) {
        return jwtUtil.getEmpUUID(token);
    }
}
