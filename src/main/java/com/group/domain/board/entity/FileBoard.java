package com.group.domain.board.entity;

import jakarta.persistence.*;
import lombok.*;

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

    @Column(name = "fboard_name")
    private String fBoardName;

    @Column(name = "fboard_size")
    private Long fBoardSize;

    @Column(name = "fboard_type")
    private String fBoardType;

    @Column(name = "fboard_path")
    private String fBoardPath;

    @ManyToOne(fetch = LAZY, cascade = ALL)
    @JoinColumn(name = "board_id")
    private Board boardId;

    @Builder
    public FileBoard(String fBoardName, Long fBoardSize, String fBoardPath, String fBoardType,
                     Board boardId) {
        this.fBoardName = fBoardName;
        this.fBoardSize = fBoardSize;
        this.fBoardPath = fBoardPath;
        this.fBoardType = fBoardType;
        this.boardId = boardId;
    }
}
