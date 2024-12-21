package com.group.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /*private final JwtUtil jwtUtil;
    private final AuthenticationConfiguration authenticationConfiguration;

    public SecurityConfig(JwtUtil jwtUtil, AuthenticationConfiguration authenticationConfiguration) {
        this.jwtUtil = jwtUtil;
        this.authenticationConfiguration = authenticationConfiguration;
    }*/

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
           return new BCryptPasswordEncoder();
    }

    /*@Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }*/

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf((auth) -> auth.disable());

        http
                .headers((headers) -> headers
                        .frameOptions()
                        .sameOrigin());
        http
                // formLogin() :
                // loinPage() : 위에서 설정한 특정 로그인 페이지 url 경로를 설정한다 = /login
                // loginProcessingUrl() : html <form> 태그에서 설정한 action 경로로 post 방식으로 보내준다. <- spring security 가 진행해준다
                .formLogin((auth) -> auth.disable());

        http
                .httpBasic((auth) -> auth.disable());

        http
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/register", "/login", "/main", "/**").permitAll()
                        .requestMatchers("/todo/**", "/hr/**", "/board/**", "/comment/**", "/mail/**", "/**").permitAll()
                        .requestMatchers("/reissue").permitAll()
                        .requestMatchers("/admin").hasRole("ADMIN")
                        .anyRequest().authenticated());

        return http.build();
    }
}