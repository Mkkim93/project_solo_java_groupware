package com.group.application.mail.dto;

import com.group.domain.mail.entity.MailTrans;
import lombok.Data;

@Data
public class MailTransDTO {

    private Integer mailTransId;
    private Integer mailBoxId;
    private Integer senderEmpId;

}
