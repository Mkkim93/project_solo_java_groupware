package com.group.domain.club.vo;

import lombok.Getter;

@Getter
public class ClubVO {

    private Integer id;
    private String clubTitle;
    private String clubContent;
    private Integer clubLimit;
    private Boolean clubActionYN;
    private Boolean clubAdminYN;
}
