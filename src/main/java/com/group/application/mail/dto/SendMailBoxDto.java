package com.group.application.mail.dto;

import com.group.domain.mail.entity.enums.MailStatus;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Data
public class SendMailBoxDto {

    private Integer mailBoxId;
    private String mailTitle;
    private Integer senderEmpId;
    private String receiverEmpName;
    private String mailStatus;
    private LocalDateTime mailDate;

    @QueryProjection
    public SendMailBoxDto(Integer mailBoxId, String mailTitle,
                          Integer senderEmpId, String receiverEmpName,
                          String mailStatus, LocalDateTime mailDate) {
        this.mailBoxId = mailBoxId;
        this.mailTitle = mailTitle;
        this.senderEmpId = senderEmpId;
        this.receiverEmpName = receiverEmpName;
        this.mailStatus = mailStatus;
        this.mailDate = mailDate;
    }
}
