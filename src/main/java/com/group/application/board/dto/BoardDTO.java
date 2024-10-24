package com.group.application.board.dto;

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
    private Employee empId;
    private Integer boardId;

    // qna 게시판 비밀번호 사용을 위한 필드 변수 생성
    private String qBoardPass;

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

    public BoardDTO(String qBoardPass) {
        this.qBoardPass = qBoardPass;
    }

    @Builder
    public void updateFromDto(String boardTitle, String boardContent, Employee empId) {
        this.boardTitle = boardTitle;
        this.boardContent = boardContent;
        this.empId = empId;
    }

    // 게시판 공용으로 사용 하기 위한 dto 생성
    public BoardDTO(String boardTitle, String boardContent) {
        this.boardTitle = boardTitle;
        this.boardContent = boardContent;
    }

    public void setAuthor(String empName) {
        this.empName = empName;
    }

    // TODO
    public void fileConverterBoard(FileBoardDTO fileBoardDTO) {
        this.id = fileBoardDTO.getBoardId();
        this.boardTitle = fileBoardDTO.getBoardTitle();
        this.boardContent = fileBoardDTO.getBoardContent();
    }

    public void freeConverterBoard(FreeBoardDTO freeBoardDTO) {
        this.id = freeBoardDTO.getBoardId();
        this.boardTitle = freeBoardDTO.getBoardTitle();
        this.boardContent = freeBoardDTO.getBoardContent();
    }

    public void noticeConverterBoard(NoticeBoardDTO noticeBoardDTO) {
        this.id = noticeBoardDTO.getBoardId();
        this.boardTitle = noticeBoardDTO.getBoardTitle();
        this.boardContent = noticeBoardDTO.getBoardContent();
    }

    public void qnaConverterBoard(QnABoardDTO qnABoardDTO) {
        this.id = qnABoardDTO.getBoardId();
        this.boardTitle = qnABoardDTO.getBoardTitle();
        this.boardContent = qnABoardDTO.getBoardContent();
        this.qBoardPass = qnABoardDTO.getQBoardPass();
    }

    public BoardDTO fromDTO(Board board) {
        this.id = board.getId();
        this.boardTitle = board.getBoardTitle();
        this.boardContent = board.getBoardContent();
        return this;
    }
}
