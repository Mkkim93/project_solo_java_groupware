package com.group.application.login.dto;

import com.group.domain.hr.entity.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@Slf4j
public class CustomUserDetails implements UserDetails {

    private final Employee employee;

    public CustomUserDetails(Employee employee) {
        this.employee = employee;
    }

    // RoleType 반환
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();
        log.info("RoleType from Employee: {}", employee.getRoleType());  // 로깅을 통해 roleType을 확인
        collection.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                log.info("empPass={}", employee.getEmpPass());
                return employee.getRoleType();
            }
        });
        log.info("collection={}", collection);
        return collection;
    }

    public String getUUID() {
        return employee.getEmpUUID();
    }

    @Override
    public String getUsername() {
        return employee.getEmpEmail();
    }

    @Override
    public String getPassword() {
        return employee.getEmpPass();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        // 계정 미접속 기간에 따라 휴면 계정으로 전환
        return true;
    }


}
