package com.group.application.board.dto;

import com.group.domain.board.entity.Board;
import com.group.domain.board.entity.FreeBoard;
import com.group.domain.hr.entity.Employee;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class FreeBoardDTO {

    private Integer id;
    private String boardTitle;
    private String empName;
    private LocalDateTime boardRegDate;
    private Integer boardViewCount;
    private String isDeleted;
    private String boardContent;
    private Integer boardId;
    private Employee empId;

    @QueryProjection
    public FreeBoardDTO(Integer id, String boardTitle, String empName, String boardContent,
                        LocalDateTime boardRegDate, Integer boardViewCount,
                        String isDeleted) {
        this.id = id;
        this.boardTitle = boardTitle;
        this.boardContent = boardContent;
        this.empName = empName;
        this.boardRegDate = boardRegDate;
        this.boardViewCount = boardViewCount;
        this.isDeleted = isDeleted;
    }

    public FreeBoardDTO fromFreeBoardDTO(Board board) {
        this.boardId = board.getId();
        return this;
    }

    public FreeBoardDTO setDto(FreeBoard freeBoard) {
        this.id = freeBoard.getBoardId().getId();
        return this;
    }
}
