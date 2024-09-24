package com.group.domain.board.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.*;

@Entity
@Table(name = "fileboard")
@Getter @NoArgsConstructor
public class FileBoard {

    @Id @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    @Column(name = "fboard_name")
    private String fBoardName;

    @Column(name = "fboard_size")
    private String fBoardSize;

    @Column(name = "fboard_type")
    private String fBoardType;

    @Column(name = "fboard_path")
    private String fBoardPath;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board boardId;
}
