package com.group.application.board.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class QnABoardDTO {

    private Integer id;
    private String boardTitle;
    private String boardContent;
    private String empName;
    private LocalDateTime boardRegDate;
    private Integer boardViewCount;
    private String isDeleted;
    private String qBoardIsSecret;
    private String qBoardPass;
    private Integer boardId;

    @QueryProjection
    public QnABoardDTO(Integer id,
                       String boardTitle,
                       String boardContent,
                       String empName,
                       String qBoardPass,
                       LocalDateTime boardRegDate,
                       Integer boardViewCount,
                       String isDeleted,
                       String qBoardIsSecret) {
        this.id = id;
        this.boardTitle = boardTitle;
        this.boardContent = boardContent;
        this.empName = empName;
        this.qBoardPass = qBoardPass;
        this.boardRegDate = boardRegDate;
        this.boardViewCount = boardViewCount;
        this.isDeleted = isDeleted;
        this.qBoardIsSecret = qBoardIsSecret;
    }

    public QnABoardDTO(String qBoardPass) {
        this.qBoardPass = qBoardPass;
    }
}