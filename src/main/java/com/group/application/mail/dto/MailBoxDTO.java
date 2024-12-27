package com.group.application.mail.dto;

import com.group.domain.mail.entity.MailBox;
import com.group.domain.mail.entity.enums.MailStatus;
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
    private String mailType;

    private LocalDateTime mailDate;
    private Integer senderEmployeeId;
    private String empName;
    private String senderName;
    private String receiverEmail;
    private String receiveEmpNames;
    private LocalDateTime senderMailDate;
    private String empEmail;

    private String favoriteType;
    private String readStatus;
    private MailStatus mailStatus;

    private Integer receiveEmpId;
    private String receiveType;

    public MailBoxDTO(Integer id, String mailTitle,
                      Integer senderEmployeeId, String senderName,
                      LocalDateTime mailDate) {
        this.id = id;
        this.mailTitle = mailTitle;
        this.senderEmployeeId = senderEmployeeId;
        this.senderName = senderName;
        this.mailDate = mailDate;
    }

    @QueryProjection
    public MailBoxDTO(Integer id, Integer senderEmployeeId,
                      String mailTitle, String mailContent, String senderName, LocalDateTime mailDate, String empEmail) {
        this.id = id;
        this.senderEmployeeId = senderEmployeeId;
        this.mailTitle = mailTitle;
        this.mailContent = mailContent;
        this.senderName = senderName;
        this.mailDate = mailDate;
        this.empEmail = empEmail;
    }

    /**
     * 메일 조회 데이터
     * @param mailTitle 메일 제목
     * @param senderEmployeeId
     */
    public MailBoxDTO(Integer id, String mailTitle, String mailContent, Integer senderEmployeeId, String receiverEmail,
                      LocalDateTime mailDate) {
        this.id = id;
        this.mailTitle = mailTitle;
        this.mailContent = mailContent;
        this.senderEmployeeId = senderEmployeeId;
        this.receiverEmail = receiverEmail;
        this.mailDate = mailDate;
    }

    public MailBoxDTO toDTO(MailBox mailBox) {
        this.id = mailBox.getId();
        this.mailTitle = mailBox.getMailTitle();
        this.mailContent = mailBox.getMailContent();
        this.mailDate = mailBox.getMailDate();
        this.senderEmployeeId = mailBox.getSenderEmployee().getId();
        return this;
    }
}
