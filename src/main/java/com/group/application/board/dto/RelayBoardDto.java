package com.group.application.board.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class RelayBoardDto {

    private Integer id;
    private String boardTitle;
    private String boardContent;
    private String empName;
    private LocalDateTime boardRegDate;
    private Integer boardViewCount;
    private String isDeleted;
    private Integer boardId;

    @QueryProjection
    public RelayBoardDto(Integer id, String boardTitle, String boardContent, String empName, LocalDateTime boardRegDate,
                         Integer boardViewCount, String isDeleted, Integer boardId) {
        this.id = id;
        this.boardTitle = boardTitle;
        this.boardContent = boardContent;
        this.empName = empName;
        this.boardRegDate = boardRegDate;
        this.boardViewCount = boardViewCount;
        this.isDeleted = isDeleted;
        this.boardId = boardId;
    }
}
