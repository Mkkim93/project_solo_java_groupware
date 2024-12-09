package com.group.application.board.dto;

import com.group.application.hr.dto.EmployeeDTO;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class FileBoardDTO {

    private Integer id;

    private String fileName;
    private Long fileSize;
    private String fileType;
    private String filePath;
    private LocalDateTime fileRegDate;
    private Integer fileBoardId;

    private String boardTitle;
    private String boardContent;
    private String empName;
    private LocalDateTime boardRegDate;
    private Integer boardViewCount;
    private String isDeleted;
    private Integer boardId;
    private String originFileName;
    private MultipartFile file;

    private EmployeeDTO employee;

    @QueryProjection
    public FileBoardDTO(Integer id, Integer boardId, String boardTitle, String boardContent,
                        String empName, LocalDateTime boardRegDate, Integer boardViewCount,
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

}
