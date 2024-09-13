package com.group.domain.club.vo;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Emp_ClubVO {

    private Integer id;
    private Integer empId;
    private Integer clubId;
    private LocalDateTime empClubJoinDate;
}
