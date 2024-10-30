package com.group.application.file;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class FileBoardStoreDTO {

    private Integer id;
    private String fileName;
    private Long fileSize;
    private String fileType;
    private String filePath;
    private LocalDateTime fileRegDate;
    private Integer fileBoardId;
    private String originFileName;

    public FileBoardStoreDTO(Integer id, String fileName, Long fileSize,
                             String fileType, String filePath, LocalDateTime fileRegDate,
                             Integer fileBoardId, String originFileName) {
        this.id = id;
        this.fileName = fileName;
        this.fileSize = fileSize;
        this.fileType = fileType;
        this.filePath = filePath;
        this.fileRegDate = fileRegDate;
        this.fileBoardId = fileBoardId;
        this.originFileName = originFileName;
    }
}
