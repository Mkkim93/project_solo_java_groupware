package com.group.application.board.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FileInfoDTO {

    private String fBoardName;
    private Long fBoardSize;
    private String fBoardType;
    private String fBoardPath;

    public FileInfoDTO(String fBoardName, Long fBoardSize, String fBoardType, String fBoardPath) {
        this.fBoardName = fBoardName;
        this.fBoardSize = fBoardSize;
        this.fBoardType = fBoardType;
        this.fBoardPath = fBoardPath;
    }
}
