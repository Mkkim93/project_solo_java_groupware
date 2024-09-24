package com.group.domain.board.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.*;

@Entity
@Table(name = "replyboard")
@Getter @NoArgsConstructor
public class ReplyBoard {

    @Id @GeneratedValue(strategy = IDENTITY)
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
    @JoinColumn(name = "board_id")
    private Board boardId;
}
