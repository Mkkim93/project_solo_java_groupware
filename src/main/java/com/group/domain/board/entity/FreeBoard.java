package com.group.domain.board.entity;

import jakarta.persistence.*;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "freeboard")
public class FreeBoard {

    public FreeBoard() {
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "boardId")
    private Board board;
}
