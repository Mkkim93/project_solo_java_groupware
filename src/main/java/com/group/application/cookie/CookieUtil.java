package com.group.application.cookie;

import com.group.application.hr.dto.EmployeeDTO;
import jakarta.servlet.http.Cookie;
import org.springframework.stereotype.Component;

@Component
public class CookieUtil {

    public Cookie saveCookie(EmployeeDTO employeeDto) {
        Cookie cookie = new Cookie("uuid", employeeDto.getEmpUUID());
        cookie.setSecure(false);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        return cookie;
    }
}
