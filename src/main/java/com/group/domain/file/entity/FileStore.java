package com.group.domain.file.entity;


import com.group.domain.board.entity.FileBoard;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.*;

@Entity
@Table(name = "filestore")
@Getter @Setter
public class FileStore {

    @Id @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "file_size")
    private Long fileSize;

    @Column(name = "file_type")
    private String fileType;

    @Column(name = "file_path")
    private String filePath;

    @Column(name = "file_regdate")
    private LocalDateTime fileRegDate;

    @Column(name = "file_orgname")
    private String OriginFileName;

    @ManyToOne
    @JoinColumn(name = "fileboard_id")
    private FileBoard fileBoardId;

    @PrePersist
    public void creatAt() {
        this.fileRegDate = LocalDateTime.now();
    }

}
