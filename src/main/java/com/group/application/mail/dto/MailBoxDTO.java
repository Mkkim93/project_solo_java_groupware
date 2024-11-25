package com.group.application.mail.dto;

import com.group.domain.mail.entity.MailBox;
import com.group.domain.mail.entity.MailStatus;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class MailBoxDTO {

    private Integer id;
    private String mailTitle;
    private String mailContent;
    private MailStatus mailStatus; // 메일 읽음 상태 ENUM (READ, NOREAD)
    private LocalDateTime senderDate;
    private Integer senderEmployeeId;

    private String senderName;

    public MailBoxDTO(String mailTitle, String mailContent, Integer senderEmployeeId, String senderName, LocalDateTime senderDate) {
        this.mailTitle = mailTitle;
        this.mailContent = mailContent;
        this.senderEmployeeId = senderEmployeeId;
        this.senderName = senderName;
        this.senderDate = senderDate;
    }

    /**
     * 메일 작성 dto
     * @param mailTitle
     * @param mailContent
     * @param senderEmployeeId
     */
    public MailBoxDTO(String mailTitle, String mailContent, Integer senderEmployeeId) {
        this.mailTitle = mailTitle;
        this.mailContent = mailContent;
        this.senderEmployeeId = senderEmployeeId;
    }

    public MailBoxDTO toDTO(MailBox mailBox) {
        this.id = mailBox.getId();
        this.mailTitle = mailBox.getMailTitle();
        this.mailContent = mailBox.getMailContent();
        this.senderDate = mailBox.getMailDate();
        this.senderEmployeeId = mailBox.getSenderEmployee().getId();
        return this;
    }
}
