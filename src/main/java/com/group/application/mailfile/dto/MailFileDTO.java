package com.group.application.mailfile.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class MailFileDTO {

    private Integer id;
    private String mailFileName;
    private Long mailFileSize;
    private LocalDateTime mailFileRegDate;
    private String mailFileOriginName;
    private String filePath;
    private Integer mailBoxFileId;
    private String mailFileType;

    @QueryProjection
    public MailFileDTO(Integer id, String mailFileName, Long mailFileSize,
                       LocalDateTime mailFileRegDate, String mailFileOriginName,
                       String filePath, Integer mailBoxFileId, String mailFileType) {
        this.id = id;
        this.mailFileName = mailFileName;
        this.mailFileSize = mailFileSize;
        this.mailFileRegDate = mailFileRegDate;
        this.mailFileOriginName = mailFileOriginName;
        this.filePath = filePath;
        this.mailBoxFileId = mailBoxFileId;
        this.mailFileType = mailFileType;
    }

}
