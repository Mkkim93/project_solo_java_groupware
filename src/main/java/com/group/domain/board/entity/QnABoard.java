package com.group.domain.board.entity;

import com.group.application.board.dto.BoardDTO;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.*;

@Entity
@Table(name = "qnaboard")
@Getter @Setter
@NoArgsConstructor
public class QnABoard {

    @Id @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    @Column(name = "qboard_pass")
    private String qBoardPass;

    @ManyToOne(fetch = LAZY, cascade = ALL)
    @JoinColumn(name = "board_id")
    private Board boardId;

    @Column(name = "qboard_issecret")
    private String qBoardIsSecret;

    @Builder
    public QnABoard(String qBoardPass, Board boardId) {
        this.qBoardPass = qBoardPass;
        this.boardId = boardId;
    }

    @PrePersist
    void createAt() {
        this.qBoardIsSecret = "Y";
    }
}
