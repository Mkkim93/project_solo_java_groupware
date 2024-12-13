package com.group.application.mail.dto;

import com.group.domain.mail.entity.enums.FavoriteType;
import com.group.domain.mail.entity.enums.ReadType;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MyMailBoxDTO {

    private Integer mailBoxId;
    private FavoriteType favoriteType;
    private ReadType readType;
    private String senderName;
    private String mailTitle;
    private LocalDateTime mailDate;

    @QueryProjection
    public MyMailBoxDTO(Integer mailBoxId, FavoriteType favoriteType, ReadType readType,
                        String senderName, String mailTitle, LocalDateTime mailDate) {
        this.mailBoxId = mailBoxId;
        this.favoriteType = favoriteType;
        this.readType = readType;
        this.senderName = senderName;
        this.mailTitle = mailTitle;
        this.mailDate = mailDate;
    }
}
