package com.group.application.board.dto;

import com.group.application.hr.dto.EmployeeDTO;
import com.group.domain.board.entity.CommentBoard;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class CommentDTO {

    private Integer id;
    private Integer boardId;
    private String empName;
    private String comContent;
    private LocalDateTime comRegDate;
    private LocalDateTime comUpdate;
    private Integer empId;
    private String boardPass;
    private String comIsDeleted;

    public CommentDTO(Integer id, String comContent,
                      String empName, LocalDateTime comRegDate, Integer empId, Integer boardId) {
        this.id = id;
        this.comContent = comContent;
        this.empName = empName;
        this.comRegDate = comRegDate;
        this.empId = empId;
        this.boardId = boardId;
    }

    public CommentDTO(Integer id, String comContent,
                      String empName, LocalDateTime comRegDate,
                      Integer empId, Integer boardId, String boardPass) {
        this.id = id;
        this.comContent = comContent;
        this.empName = empName;
        this.comRegDate = comRegDate;
        this.empId = empId;
        this.boardId = boardId;
        this.boardPass = boardPass;
    }

    public CommentDTO(Integer boardId) {
        this.boardId = boardId;
    }

    // 댓글 수정 최종 폼 데이터 dto 로 전달
    public CommentDTO toDto(CommentBoard e) {
        this.id = e.getId();
        this.comContent = e.getComContent();
        this.comUpdate = e.getComUpdate();
        this.boardId = e.getBoard().getId();
        this.comIsDeleted = "N";
        return this;
    }
}
