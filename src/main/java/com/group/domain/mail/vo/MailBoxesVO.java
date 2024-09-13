package com.group.domain.mail.vo;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MailBoxesVO {

    private Integer id;
    private String mailTitle;
    private String mailContent;
    private String mailStatus;
    private LocalDateTime mailDate;
    private Integer mailFileId;
}
