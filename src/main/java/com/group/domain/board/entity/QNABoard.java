package com.group.domain.board.entity;

import jakarta.persistence.*;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "qnaboard")
public class QNABoard {

    public QNABoard() {
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    @Column(name = "qboard_pass")
    private Integer qBoardPass;

    @ManyToOne
    @JoinColumn(name = "boardId")
    private Board board;
}
