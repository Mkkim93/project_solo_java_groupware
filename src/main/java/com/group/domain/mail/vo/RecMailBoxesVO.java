package com.group.domain.mail.vo;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class RecMailBoxesVO {

    private Integer id;
    private LocalDateTime recDate;
    private Integer mailboxesId;
    private Integer empId;
}
