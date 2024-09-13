package com.group.domain.messenger.vo;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MessengerVO {

    private Integer id;
    private Integer msgSendId;
    private Integer msgReceiveId;
    private String msgContent;
    private LocalDateTime msgSendTime;
    private LocalDateTime msgReceiveTime;
}
