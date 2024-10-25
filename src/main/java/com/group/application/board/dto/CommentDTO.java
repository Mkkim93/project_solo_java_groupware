package com.group.application.board.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@ToString
@NoArgsConstructor
public class CommentDTO {

    private Integer boardId;
    private Integer id;
    private String empName;
    private String comContent;
    private LocalDateTime comRegDate;
    private Integer empId;

    public CommentDTO(Integer id, String comContent,
                      String empName, LocalDateTime comRegDate, Integer empId, Integer boardId) {
        this.id = id;
        this.comContent = comContent;
        this.empName = empName;
        this.comRegDate = comRegDate;
        this.empId = empId;
        this.boardId = boardId;
    }
}
