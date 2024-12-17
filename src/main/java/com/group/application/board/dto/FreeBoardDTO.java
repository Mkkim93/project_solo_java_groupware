package com.group.application.board.dto;

import com.group.application.hr.dto.EmployeeDTO;
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
    private String boardContent;
    private LocalDateTime boardRegDate;
    private Integer boardViewCount;
    private String isDeleted;
    private Integer boardId;
    private EmployeeDTO employee;

    @QueryProjection
    public FreeBoardDTO(Integer id, Integer boardId, String boardTitle,
                        String empName, String boardContent,
                        LocalDateTime boardRegDate, Integer boardViewCount,
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

    public FreeBoardDTO updateBoard(FreeBoardDTO freeBoardDto, EmployeeDTO employeeDto) {
        this.boardTitle = freeBoardDto.getBoardTitle();
        this.boardContent = freeBoardDto.getBoardContent();
        this.employee = employeeDto;
        return this;
    }
}
