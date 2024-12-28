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
@Table(name = "noticeboard")
@Getter @Setter
@NoArgsConstructor
@NamedEntityGraph(name = "Board.all", attributeNodes = @NamedAttributeNode("board"))
public class NoticeBoard {

    @Id @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    @ManyToOne(fetch = LAZY, cascade = ALL)
    @JoinColumn(name = "board_id")
    private Board board;

    @Builder
    public NoticeBoard(Board board, Integer id) {
        this.id = id;
        this.board = board;
    }
}
