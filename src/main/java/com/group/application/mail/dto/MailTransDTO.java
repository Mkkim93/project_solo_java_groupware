package com.group.application.mail.dto;

import com.group.domain.mail.entity.MailTrans;
import com.group.domain.mail.entity.enums.FavoriteType;
import com.group.domain.mail.entity.enums.ReadStatus;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class MailTransDTO {

    private Integer mailTransId;
    private FavoriteType favoriteType;
    private ReadStatus readStatus;
    private Integer mailBoxId;
    private String mailTitle;
    private LocalDateTime mailDate;
    private String senderName;
    private String mailTypes;
    private Integer receiveEmpId;
    private String receiveType;

    // 외부 entity 사용 메일 발송 상태 :
    private String mailStatus;

    @QueryProjection
    public MailTransDTO(Integer mailBoxId, FavoriteType favoriteType, ReadStatus readStatus,
                        String senderName, String mailTitle, LocalDateTime mailDate) {
        this.mailBoxId = mailBoxId;
        this.favoriteType = favoriteType;
        this.readStatus = readStatus;
        this.senderName = senderName;
        this.mailTitle = mailTitle;
        this.mailDate = mailDate;
    }

}
