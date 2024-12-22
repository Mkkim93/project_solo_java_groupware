package com.group.application.mail.dto;

import com.group.domain.mail.entity.MailBox;
import com.group.domain.mail.entity.enums.FavoriteType;
import com.group.domain.mail.entity.enums.ReadStatus;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class MailBoxDTO {

    private Integer id;
    private String mailTitle;
    private String mailContent;
    private String mailType;

    private LocalDateTime senderDate;
    private Integer senderEmployeeId;
    private String empName;
    private String senderName;
    private String receiverEmail;

    private FavoriteType favoriteType;
    private ReadStatus readStatus;


    public MailBoxDTO(Integer id, String mailTitle,
                      Integer senderEmployeeId, String senderName,
                      LocalDateTime senderDate) {
        this.id = id;
        this.mailTitle = mailTitle;
        this.senderEmployeeId = senderEmployeeId;
        this.senderName = senderName;
        this.senderDate = senderDate;
    }

    @QueryProjection
    public MailBoxDTO(Integer id, Integer senderEmployeeId,
                      String mailTitle, String mailContent, String senderName, LocalDateTime senderDate) {
        this.id = id;
        this.senderEmployeeId = senderEmployeeId;
        this.mailTitle = mailTitle;
        this.mailContent = mailContent;
        this.senderName = senderName;
        this.senderDate = senderDate;
    }

    /**
     * 메일 작성 dto
     * @param mailTitle
     * @param mailContent
     * @param senderEmployeeId
     */
    public MailBoxDTO(String mailTitle, String mailContent, Integer senderEmployeeId, String receiverEmail,
                      LocalDateTime senderDate) {
        this.mailTitle = mailTitle;
        this.mailContent = mailContent;
        this.senderEmployeeId = senderEmployeeId;
        this.receiverEmail = receiverEmail;
        this.senderDate = senderDate;
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
