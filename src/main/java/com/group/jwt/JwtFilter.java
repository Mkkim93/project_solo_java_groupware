package com.group.jwt;

import com.group.application.login.dto.CustomUserDetails;
import com.group.domain.hr.entity.Employee;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.io.PrintWriter;

@Slf4j
@RequiredArgsConstructor
public class JwtFilter {

    /*private final JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        // 헤더에서 access키에 담긴 토큰을 꺼냄
        String accessToken = request.getHeader("access");
        log.info("accessToken={}", accessToken); // TODO 두번 호출 되는 이유 나중에 디버그?
        // 토큰이 없다면 다음 필터로 넘김
        if (accessToken == null) {
            filterChain.doFilter(request, response);
            return;
        }

        // 토큰 만료 여부 확인, 만료시 다음 필터로 넘기지 않음
        try {
            jwtUtil.isExpired(accessToken);
        } catch (ExpiredJwtException e) {

            //response body
            PrintWriter writer = response.getWriter();
            writer.print("access token expired");

            //response status code
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        // 토큰이 access인지 확인 (발급시 페이로드에 명시)
        String tokenType = jwtUtil.getTokenType(accessToken);

        if (!tokenType.equals("access")) {

            //response body
            PrintWriter writer = response.getWriter();
            writer.print("invalid access token");

            //response status code
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        // empEmail, roleType 값을 획득
        String empEmail = jwtUtil.getUsername(accessToken);
        String roleType = jwtUtil.getRole(accessToken);
        String empUUID = jwtUtil.getEmpUUID(accessToken);
        log.info("empEmail by access={}", empEmail);
        log.info("roleType by access={}", roleType);

        Employee employee = new Employee();
        employee.setEmpEmail(empEmail);
        employee.setRoleType(roleType);
        employee.setEmpUUID(empUUID);
        CustomUserDetails customUserDetails = new CustomUserDetails(employee);

        Authentication authToken = new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authToken);
        log.info("SecurityContextHolder={}", SecurityContextHolder.getContext().getAuthentication());

        filterChain.doFilter(request, response);
    }*/
}