package com.group.config.security;

import com.group.jwt.JwtFilter;
import com.group.jwt.JwtUtil;
import com.group.jwt.LoginFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.http.SessionCreationPolicy.*;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtUtil jwtUtil;
    private final AuthenticationConfiguration authenticationConfiguration;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf((auth) -> auth.disable());

        http
                .headers((headers) -> headers
                        .frameOptions()
                        .sameOrigin());

        http
                .formLogin((auth) -> auth.loginPage("/login"));
        http
                .httpBasic((auth) -> auth.disable());

        http
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/files/**","/css/**", "/js/**", "/att/in", "att/out",
                                "/images/**","/register", "/", "/login", "/user","/hr/**",
                                "/todo/**", "/hr/atten", "/board/**", "/comment/**", "/**").permitAll()
                        .requestMatchers("/admin").hasAuthority("ADMIN")
                        .anyRequest().authenticated());

        http
                .addFilterBefore(new JwtFilter(jwtUtil), LoginFilter.class);

        http
                .addFilterAt(new LoginFilter(authenticationManager(authenticationConfiguration), jwtUtil),
                        UsernamePasswordAuthenticationFilter.class);

        // 세션 설정 (jwt 에서는 항상 세션을 STATELESS 상태로 만든다)
        http
                .sessionManagement((session) -> session.sessionCreationPolicy(STATELESS));

        return http.build();
    }
}
