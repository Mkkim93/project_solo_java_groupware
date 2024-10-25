package com.group.domain.board.entity;

import com.group.application.board.dto.FreeBoardDTO;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

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
    private Board boardId;

    @Builder
    public FreeBoard(Integer id, Board boardId) {
        this.id = id;
        this.boardId = boardId;
    }
}
