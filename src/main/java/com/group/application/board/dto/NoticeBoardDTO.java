package com.group.application.board.dto;

import com.group.application.hr.dto.EmployeeDTO;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aspectj.weaver.ast.Not;
import org.springframework.cglib.core.Local;

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
    private EmployeeDTO employee;

    @QueryProjection
    public NoticeBoardDTO(Integer id, Integer boardId,
                          String boardTitle, String empName, String boardContent,
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

    public NoticeBoardDTO updateBoard(NoticeBoardDTO noticeBoardDto, EmployeeDTO employeeDto) {
        this.boardTitle = noticeBoardDto.getBoardTitle();
        this.boardContent = noticeBoardDto.getBoardContent();
        this.employee = employeeDto;
        return this;
    }

    public NoticeBoardDTO(Integer id, String boardTitle, LocalDateTime boardRegDate) {
        this.id = id;
        this.boardTitle = boardTitle;
        this.boardRegDate = boardRegDate;
    }
}
