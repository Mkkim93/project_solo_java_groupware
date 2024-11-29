package com.group.config.security;

import com.group.domain.hr.repository.EmployeeRepository;
import com.group.domain.hr.entity.Employee; // Assuming you have an Employee entity
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.web.servlet.function.RequestPredicates.headers;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public SecurityConfig(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            Employee employee = employeeRepository.findByEmpEmail(username);  // Assuming `findByEmail` is implemented
            if (employee == null) {
                throw new UsernameNotFoundException("User not found with email: " + username);
            }

            return User.builder()
                    .username(employee.getEmpEmail())
                    .password(employee.getEmpPass())
                    .roles("ADMIN")
                    .build();
        };
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/**", "/att/**").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin().loginPage("/login");
        http
                .headers().frameOptions().sameOrigin();

        return http.build();
    }
}
