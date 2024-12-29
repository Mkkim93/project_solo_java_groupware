package com.group.jwt;

import com.group.application.login.dto.CustomUserDetails;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;

import java.util.Collection;
import java.util.Iterator;

@Slf4j
@RequiredArgsConstructor
public class LoginFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse res) throws AuthenticationException {

        /**
         * 사용자 지정 parameter 로 변경 (jwt basic name (username) -> entity field name(empEmail))
         * - username -> empEmail
         * - password -> empPass
         */
        String empEmail = req.getParameter("empEmail");
        String empPass = req.getParameter("empPass");
        /*String username = obtainUsername(req);
        String password = obtainPassword(req);*/

        log.info("attemptAuthentication Method ?");
        log.info("empEmail={}, empPass={}", empEmail, empPass);

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(empEmail, empPass);

        return authenticationManager.authenticate(authToken);
    }

    // 로그인 성공
    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain, Authentication authentication) throws IOException, ServletException {

        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        String empEmail = customUserDetails.getUsername();
        log.info("empEmail={}", empEmail);
        // roleType 을 꺼내기 위해 authentication 인스턴스를 객체로 변환 후 iterator 를 실행하고 iterator 를 실행 된 인스턴스를 다시 next() 를 통해
        // 사용자에게 부여된 권한(권한 또는 역할)을 나타내는 인터페이스 GrantedAuthority 객체에서 생성된 auth 를 getAuthority() 메서드를 통해
        // 최종 roleType 을 받는다
        String uuid = customUserDetails.getUUID();
        log.info("empUUID={}" ,uuid);
        Collection<? extends GrantedAuthority> authorities =
                authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
        GrantedAuthority auth = iterator.next();
        String role = auth.getAuthority();
        log.info("roleType={}", role);

        // expired time : 24 * 60 * 60 * 1000L (24 hours)
        String access = jwtUtil.createJwt("access", empEmail,  uuid, role, 60 * 60 * 24 * 100L); // 최종 토큰 발급
        String refresh = jwtUtil.createJwt("refresh", empEmail, uuid, role, 60 * 60 * 24 * 100L); // 최종 토큰 발급

        res.setHeader("access", access);
        res.addCookie(createCookie("refresh", refresh));

        log.info("login success");
        log.info("access Token={}", access);
        log.info("refresh Token={}", refresh);
    }

    private Cookie createCookie(String key, String value) {
        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(24*60*60);
        cookie.setHttpOnly(true);
        cookie.setSecure(false);
        cookie.setPath("/");
        return cookie;
    }

    // 로그인 실패
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest req,
                                              HttpServletResponse res,
                                              AuthenticationException failed) throws IOException, ServletException {

        res.setStatus(401); // 토큰 발급 실패 시 401 응답 전달
    }
}
