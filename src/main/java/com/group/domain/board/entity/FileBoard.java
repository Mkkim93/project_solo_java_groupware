package com.group.domain.board.entity;

import jakarta.persistence.*;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "fileboard")
public class FileBoard {

    public FileBoard() {
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    @Column(name = "fboard_name")
    private String fBoardName;

    @Column(name = "fboard_size")
    private Long fBoardSize;

    @Column(name = "fboard_type")
    private String fBoardType;

    @ManyToOne
    @JoinColumn(name = "boardId")
    private Board board;
}
