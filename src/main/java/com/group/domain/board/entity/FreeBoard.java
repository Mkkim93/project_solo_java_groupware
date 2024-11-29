package com.group.domain.board.entity;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.*;

@Entity
@Table(name = "freeboard")
@Getter @Setter
@NoArgsConstructor
public class FreeBoard {

    @Id @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    @ManyToOne(cascade = ALL, fetch = LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @Builder
    public FreeBoard(Integer id, Board board) {
        this.id = id;
        this.board = board;
    }
}
