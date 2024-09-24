package com.group.domain.board.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.*;

@Entity
@Table(name = "qnaboard")
@Getter @NoArgsConstructor
public class QnABoard {

    @Id @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    @Column(name = "qboard_pass")
    private Integer qBoardPass;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board boardId;
}
