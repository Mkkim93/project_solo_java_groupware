package com.group.domain.board.entity;

import com.group.application.board.dto.FileBoardDTO;
import com.group.domain.file.entity.FileStore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.FetchType.*;
import static jakarta.persistence.GenerationType.*;

@Entity
@Table(name = "fileboard")
@Getter @Setter
@NoArgsConstructor
public class FileBoard {

    @Id @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    @ManyToOne(fetch = LAZY, cascade = ALL)
    @JoinColumn(name = "board_id")
    private Board boardId;

    @OneToMany(mappedBy = "fileBoardId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<FileStore> fileStores = new ArrayList<>();

    @Builder
    public FileBoard(Integer id, Board boardId) {
        this.id = id;
        this.boardId = boardId;
    }

}
