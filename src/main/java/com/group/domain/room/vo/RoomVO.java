package com.group.domain.room.vo;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class RoomVO {

    private Integer id;
    private Integer roomNo;
    private String roomType;
    private String roomTitle;
    private Integer roomSize;
    private String roomMember;
    private LocalDateTime roomStartTime;
    private LocalDateTime roomEndTime;
    private LocalDateTime roomCreateTime;
    private LocalDateTime roomUpdateTime;
    private Integer empId;
}
