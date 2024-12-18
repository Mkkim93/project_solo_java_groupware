package com.group.application.board.dto;

import com.group.application.hr.dto.EmployeeDTO;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class CommentDTO {

    private Integer id;
    private Integer boardId;
    private String empName;
    private String comContent;
    private LocalDateTime comRegDate;
    private Integer empId;
    private String boardPass;

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
}
