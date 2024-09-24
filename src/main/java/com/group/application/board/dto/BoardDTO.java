package com.group.application.board.dto;

import com.group.application.hr.dto.EmployeeDTO;
import com.group.domain.hr.entity.Employee;
import jakarta.persistence.Column;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
public class BoardDTO {

    private Integer id;

    private String boardTitle;

    private String boardWriter;

    private String boardContent;

    private LocalDateTime boardRegDate;

    private LocalDateTime boardUpdateDate;

    private LocalDateTime boardDeleteDate;

    private Integer boardViewCount;

    private Integer empId;

    public BoardDTO(){}

    // 게시글 작성
    public BoardDTO(String boardTitle, String boardWriter, String boardContent,
                    LocalDateTime boardRegDate, Integer boardViewCount, Integer empId) {
        this.boardTitle = boardTitle;
        this.boardWriter = boardWriter;
        this.boardContent = boardContent;
        this.boardRegDate = boardRegDate;
        this.boardViewCount = boardViewCount;
        this.empId = empId;
    }
}
