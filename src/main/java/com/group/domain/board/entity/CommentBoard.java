package com.group.domain.board.entity;

import com.group.application.board.dto.CommentDTO;
import com.group.domain.hr.entity.Employee;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

import static jakarta.persistence.FetchType.*;
import static jakarta.persistence.GenerationType.*;

@Entity
@Table(name = "commentboard")
@Getter @Setter
@NoArgsConstructor
public class CommentBoard {

    @Id @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    @Column(name = "com_content")
    private String comContent;

    @Column(name = "com_regdate")
    private LocalDateTime comRegDate;

    @Column(name = "com_update")
    private LocalDateTime comUpdate;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "emp_id")
    private Employee employee;

    @PrePersist
    void createComment() {
        this.comRegDate = LocalDateTime.now();
    }

    @Builder
    public CommentBoard(CommentDTO dto) {
        this.id = dto.getId();
        this.comContent = dto.getComContent();
        this.comRegDate = dto.getComRegDate();
        this.employee = Employee.builder()
                .id(dto.getEmpId())
                .build();
        this.board = Board.builder()
                .id(dto.getBoardId())
                .build();
    }
}
