package com.group.application.board.dto;

import com.group.application.hr.dto.EmployeeDTO;
import com.group.domain.board.entity.Board;
import com.group.domain.hr.entity.Employee;
import com.querydsl.core.annotations.QueryProjection;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardDTO {

    // 모든 게시판 공용
    private Integer id;
    private String boardTitle;
    private String boardContent;
    private String empName;
    private LocalDateTime boardRegDate;
    private LocalDateTime boardUpdateDate;
    private LocalDateTime boardDeleteDate;
    private Integer boardViewCount;
    private String isDeleted;
    private EmployeeDTO employee;
    private Integer boardId;
    private String comContent;
    private String comRegDate;

    // qna 게시판 비밀번호 사용을 위한 필드 변수 생성
    private String boardPass;

    // 파일 게시판을 위한 필드 변수 생성
    private String fBoardName;
    private Long fBoardSize;
    private String fBoardType;
    private String fBoardPath;
    private String fOriginFileName;

    //자유 게시판 조회를 위한 dto 생성
    @QueryProjection
    public BoardDTO(Integer id, String boardTitle, String boardContent,
                    String empName, LocalDateTime boardRegDate, Integer boardViewCount,
                    String isDeleted) {
        this.id = id;
        this.boardTitle = boardTitle;
        this.boardContent = boardContent;
        this.empName = empName;
        this.boardRegDate = boardRegDate;
        this.boardViewCount = boardViewCount;
        this.isDeleted = isDeleted;
    }

    public BoardDTO(String boardPass) {
        this.boardPass = boardPass;
    }

    // 게시판 공용 사용을 위한 dto 생성
    public BoardDTO(String boardTitle, String boardContent, LocalDateTime boardRegDate) {
        this.boardTitle = boardTitle;
        this.boardContent = boardContent;
    }

    public BoardDTO toDto(Board e) {
        this.id = e.getId();
        this.boardTitle = e.getBoardTitle();
        this.boardContent = e.getBoardContent();
        this.employee = e.getEmployee().toDto(e.getEmployee());
        return this;
    }

    public void convertToFileBoardDto(FileBoardDTO dto) {
        this.id = dto.getBoardId();
        this.boardTitle = dto.getBoardTitle();
        this.boardContent = dto.getBoardContent();
        this.employee = dto.getEmployee();
    }

    public void convertToFreeBoardDto(FreeBoardDTO dto) {
        this.id = dto.getBoardId();
        this.boardTitle = dto.getBoardTitle();
        this.boardContent = dto.getBoardContent();
        this.employee = dto.getEmployee();
    }

    public void convertToNoticeBoardDto(NoticeBoardDTO dto) {
        this.id = dto.getBoardId();
        this.boardTitle = dto.getBoardTitle();
        this.boardContent = dto.getBoardContent();
        this.employee = dto.getEmployee();
    }

    public void convertToQnaBoardDto(QnABoardDTO dto) {
        this.id = dto.getBoardId();
        this.boardTitle = dto.getBoardTitle();
        this.boardContent = dto.getBoardContent();
        this.boardPass = dto.getBoardPass();
        this.employee = dto.getEmployee();
    }

    public EmployeeDTO setterEmpDto(EmployeeDTO dto) {
        return employee = dto;
    }

    public BoardDTO updateBoard(BoardDTO boardDto, EmployeeDTO employeeDto) {
        this.boardTitle = boardDto.getBoardTitle();
        this.boardContent = boardDto.getBoardContent();
        this.employee = employeeDto;
        return this;
    }
}
