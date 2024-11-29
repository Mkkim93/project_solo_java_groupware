package com.group.application.board.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class NoticeBoardDTO {

    private Integer id;
    private String boardTitle;
    private String empName;
    private String boardContent;
    private LocalDateTime boardRegDate;
    private Integer boardViewCount;
    private String isDeleted;
    private Integer boardId;
    private Integer employee;

    @QueryProjection
    public NoticeBoardDTO(Integer id,
                          Integer boardId,
                          String boardTitle,
                          String boardContent,
                          String empName,
                          LocalDateTime boardRegDate,
                          Integer boardViewCount,
                          String isDeleted) {
        this.id = id;
        this.boardId = boardId;
        this.boardTitle = boardTitle;
        this.boardContent = boardContent;
        this.empName = empName;
        this.boardRegDate = boardRegDate;
        this.boardViewCount = boardViewCount;
        this.isDeleted = isDeleted;
    }
}
