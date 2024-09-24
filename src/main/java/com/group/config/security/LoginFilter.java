package com.group.config.security;

import com.group.application.jwt.JWTUtil;
import com.group.application.login.dto.CustomUserDetails;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

public class LoginFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;

    public LoginFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {

        String empEmail = obtainUsername(request);
        String password = obtainPassword(request);

        System.out.println("Attempting login with email: " + empEmail);
        System.out.println("Attempting login with password: " + password);

        // parameters 3번째에 roleType
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(empEmail, password, null);

        return authenticationManager.authenticate(authToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authentication) throws IOException, ServletException {
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        String empEmail = customUserDetails.getUsername();

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
        GrantedAuthority auth = iterator.next();

        String role = auth.getAuthority();

        String token = jwtUtil.createJwt(empEmail, role, 3600 * 3600 * 10L);
        response.addHeader("Authorization", "Bearer " + token); // TODO 띄어쓰기 하는 이유 (?)
    }

    // Authorization : 타입 인증 토큰
    // Authorization : Bearer 인증 토큰 string
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request,
                                              HttpServletResponse response,
                                              AuthenticationException failed) throws IOException, ServletException {
        response.setStatus(401); // login 실패 시 401 status 응답
    }

    /** custom : obtainUsername (username -> empEmail ) : 로그인 id empEmail 로 수정 */
    @Override
    protected String obtainUsername(HttpServletRequest request) {
        return request.getParameter("empEmail");
    }

    @Override
    protected String obtainPassword(HttpServletRequest request) {
        return request.getParameter("empPass");
    }
}
