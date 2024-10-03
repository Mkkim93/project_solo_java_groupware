package com.group.application.board.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class FileBoardDTO {

    private Integer id;

    private String fBoardName;
    private Long fBoardSize;
    private String fBoardType;
    private String fBoardPath;

    private String boardTitle;
    private String boardContent;
    private String empName;
    private LocalDateTime boardRegDate;
    private Integer boardViewCount;
    private String isDeleted;

    private FileInfoDTO fileInfoDTO;

    @QueryProjection
    public FileBoardDTO(Integer id, String boardTitle, String boardContent,
                        String empName, LocalDateTime boardRegDate, Integer boardViewCount,
                        String isDeleted, String fBoardName, Long fBoardSize, String fBoardType,
                        String fBoardPath) {
        this.id = id;
        this.boardTitle = boardTitle;
        this.boardContent = boardContent;
        this.empName = empName;
        this.boardRegDate = boardRegDate;
        this.boardViewCount = boardViewCount;
        this.isDeleted = isDeleted;
        this.fBoardName = fBoardName;
        this.fBoardSize = fBoardSize;
        this.fBoardType = fBoardType;
        this.fBoardPath = fBoardPath;
    }

    public void setFileInfo(FileInfoDTO fileInfoDTO) {
        this.fBoardName = fileInfoDTO.getFBoardName();
        this.fBoardSize = fileInfoDTO.getFBoardSize();
        this.fBoardType = fileInfoDTO.getFBoardType();
        this.fBoardPath = fileInfoDTO.getFBoardPath();
    }
}
