package com.group.application.mail.dto;

import com.group.domain.mail.entity.enums.FavoriteType;
import com.group.domain.mail.entity.enums.ReadStatus;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MyMailBoxDTO {

    private Integer mailBoxId;
    private FavoriteType favoriteType;
    private ReadStatus readStatus;
    private String senderName;
    private String mailTitle;
    private LocalDateTime mailDate;

    @QueryProjection
    public MyMailBoxDTO(Integer mailBoxId, FavoriteType favoriteType, ReadStatus readStatus,
                        String senderName, String mailTitle, LocalDateTime mailDate) {
        this.mailBoxId = mailBoxId;
        this.favoriteType = favoriteType;
        this.readStatus = readStatus;
        this.senderName = senderName;
        this.mailTitle = mailTitle;
        this.mailDate = mailDate;
    }
}
