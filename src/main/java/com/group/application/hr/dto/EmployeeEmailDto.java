package com.group.application.hr.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmployeeEmailDto {

    private String empName;
    private String empMail;

    public EmployeeEmailDto(String empName, String empMail) {
        this.empName = empName;
        this.empMail = empMail;
    }

    public String format() {
        return empName + " <" + empMail + ">";
    }
}
