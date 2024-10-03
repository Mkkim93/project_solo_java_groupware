package com.group.application.hr.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class EmployeeDTO {

    private String empEmail;
    private String empPass;
    private String empName;
    private String empRegNo;
    private String userEmail;
    private String empNickName;

    public EmployeeDTO(String empEmail, String empPass, String empName, String empRegNo,
                        String userEmail, String empNickName) {
        this.empEmail = empEmail;
        this.empPass = empPass;
        this.empName = empName;
        this.empRegNo = empRegNo;
        this.userEmail = userEmail;
        this.empNickName = empNickName;
    }

}
