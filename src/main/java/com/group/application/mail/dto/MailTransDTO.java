package com.group.application.mail.dto;

import com.group.domain.mail.entity.enums.FavoriteType;
import com.group.domain.mail.entity.enums.MailStatus;
import com.group.domain.mail.entity.enums.ReadStatus;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class MailTransDTO {

    private Integer id;
    private FavoriteType favoriteType;
    private ReadStatus readStatus;
    private Integer mailBoxId;
    private String mailTitle;
    private LocalDateTime mailDate;
    private String senderName;
    private String mailTypes;
    private Integer receiveEmpId;
    private String receiveType;
    private String receiveEmpName;

    // 외부 entity 사용 메일 발송 상태 :
    private MailStatus mailStatus;

    @QueryProjection
    public MailTransDTO(Integer id, FavoriteType favoriteType, ReadStatus readStatus,
                        String senderName, String mailTitle, LocalDateTime mailDate) {
        this.id = id;
        this.favoriteType = favoriteType;
        this.readStatus = readStatus;
        this.senderName = senderName;
        this.mailTitle = mailTitle;
        this.mailDate = mailDate;
    }

}
