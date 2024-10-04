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
public class NoticeBoard {

    @Id @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    @ManyToOne(fetch = LAZY, cascade = ALL)
    @JoinColumn(name = "board_id")
    private Board boardId;

    @Builder
    public NoticeBoard(Board boardId, Integer id) {
        this.id = id;
        this.boardId = boardId;
    }
}
