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
    private Board boardId;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "emp_id")
    private Employee empId;

    @PrePersist
    void createComment() {
        this.comRegDate = LocalDateTime.now();
    }

    // 댓글 수정
    void UpdateComment(String comContent) {
        this.comContent = comContent;
    }

    @Builder
    public CommentBoard(CommentDTO commentDTO) {
        this.id = commentDTO.getId();
        this.comContent = commentDTO.getComContent();
        this.comRegDate = commentDTO.getComRegDate();
        this.empId = Employee.builder()
                .id(commentDTO.getEmpId())
                .build();
        this.boardId = Board.builder()
                .id(commentDTO.getBoardId())
                .build();
    }
}
