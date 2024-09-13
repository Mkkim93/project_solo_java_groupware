package com.group.domain.mail.vo;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class SendMailBoxesVO {

    private Integer id;
    private LocalDateTime sendDate;
    private Integer mailboxesId;
    private Integer empId;
}
