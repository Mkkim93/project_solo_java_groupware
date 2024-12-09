package com.group.application.board.dto;

import com.group.application.hr.dto.EmployeeDTO;
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
    private String boardSecret;
    private String boardPass;
    private Integer boardId;
    private EmployeeDTO employee;

    @QueryProjection
    public QnABoardDTO(Integer id,
                       Integer boardId,
                       String boardTitle,
                       String boardContent,
                       String empName,
                       String boardPass,
                       LocalDateTime boardRegDate,
                       Integer boardViewCount,
                       String isDeleted,
                       String boardSecret) {
        this.id = id;
        this.boardId = boardId;
        this.boardTitle = boardTitle;
        this.boardContent = boardContent;
        this.empName = empName;
        this.boardPass = boardPass;
        this.boardRegDate = boardRegDate;
        this.boardViewCount = boardViewCount;
        this.isDeleted = isDeleted;
        this.boardSecret = boardSecret;
    }
}
