package com.group.domain.board.entity;

import com.group.domain.hr.entity.Employee;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static jakarta.persistence.FetchType.*;
import static jakarta.persistence.GenerationType.*;

@Entity
@Table(name = "commentboard")
@Getter
@NoArgsConstructor
public class CommentBoard {

    @Id @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    @Column(name = "cboard_content")
    private String cBoardContent;

    @Column(name = "cboard_regdate")
    private LocalDateTime cBoardRegDate;

    @Column(name = "cboard_update")
    private LocalDateTime cBoardUpdate;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "board_id")
    private Board boardId;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "emp_id")
    private Employee empId;

    @PrePersist
    void createComment() {
        this.cBoardRegDate = LocalDateTime.now();
    }

    // 댓글 수정
    void UpdateComment(String cBoardContent) {
        this.cBoardContent = cBoardContent;
    }
}
