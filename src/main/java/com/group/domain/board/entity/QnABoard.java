package com.group.domain.board.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.GenerationType.*;

@Entity
@Table(name = "qnaboard")
@Getter @Setter
@NoArgsConstructor
public class QnABoard {

    @Id @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    @Column(name = "board_pass")
    private String boardPass;

    @ManyToOne(cascade = ALL)
    @JoinColumn(name = "board_id")
    private Board board;

    @Column(name = "board_secret")
    private String boardSecret;

    @Builder
    public QnABoard(String boardPass, Board board, Integer id, String boardSecret) {
        this.id = id;
        this.boardPass = boardPass;
        this.board = board;
        this.boardSecret = boardSecret;
    }

    @PrePersist
    void createAt() {
        if (boardPass.isEmpty()) {
            boardSecret = "N";
        } else {
            boardSecret = "Y";
        }
    }
}
