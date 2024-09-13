package com.group.domain.board.entity;

import jakarta.persistence.*;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "noticeboard")
public class NoticeBoard {

    public NoticeBoard() {
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "boardId")
    private Board board;
}
