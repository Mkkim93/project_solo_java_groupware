package com.group.domain.board.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "replyboard")
public class ReplyBoard {

    public ReplyBoard() {
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    @Column(name = "rboard_title")
    private String rBoardTitle;

    @Column(name = "rboard_content")
    private String rBoardContent;

    @Column(name = "rboard_writer")
    private String rBoardWriter;

    @Column(name = "rboard_regdate")
    private LocalDateTime rBoardRegDate;

    @ManyToOne
    @JoinColumn(name = "boardId")
    private Board board;
}
