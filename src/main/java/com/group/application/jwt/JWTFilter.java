package com.group.application.jwt;

import com.group.application.login.dto.CustomUserDetails;
import com.group.domain.hr.entity.Employee;
import com.group.domain.hr.enums.EmpIsAdmin;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Component
public class JWTFilter extends OncePerRequestFilter {

    private final JWTUtil jwtUtil;

    public JWTFilter(JWTUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        // request 에서 Authorization 헤더를 찾는다
        String authorization = request.getHeader("Authorization");

        if (authorization == null || !authorization.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            log.info("token null");
            return;
//            System.out.println("token null");
        }
        log.info("authorization now");
        // authorization 에 토큰은 Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9... 와 같은 형식으로 저장 되는데
        // 아래의 split 메서드를 사용하면 인덱스 [1] 인 eyJhbGc... 만을 추출할 수 있다. (index[0] = Bearer)
        String token = authorization.split(" ")[1];

        if (jwtUtil.isExpired(token)) {
            log.info("token expired");
            filterChain.doFilter(request, response);
            return;
        }

        String empEmail = jwtUtil.getUsername(token);
        String role = jwtUtil.getRole(token);
        System.out.println("role = " + role);

        Employee employee = new Employee();
        employee.setEmpEmail(empEmail);
        employee.setEmpPass("temppassword");
        employee.setEmpIsAdmin(role);

        CustomUserDetails customUserDetails = new CustomUserDetails(employee);

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(customUserDetails,
                null, customUserDetails.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authToken);

        filterChain.doFilter(request, response);
    }
}
