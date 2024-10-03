package com.group.domain.board.entity;

import com.group.application.board.dto.BoardDTO;
import com.group.domain.hr.entity.Employee;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.relational.core.sql.In;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static jakarta.persistence.CascadeType.*;
import static jakarta.persistence.FetchType.*;
import static jakarta.persistence.GenerationType.*;

@Entity
@Table(name = "board")
@Getter @Setter
@NoArgsConstructor
// @EntityListeners(AuditingEntityListener.class)
public class Board {

    @Id @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    @Column(name = "board_title")
    private String boardTitle;

    @Lob
    @Column(name = "board_content")
    private String boardContent;

    // @CreatedDate
    @Column(name = "board_regdate", updatable = false, nullable = false)
    private LocalDateTime boardRegDate;

    @Column(name = "board_updatedate")
    private LocalDateTime boardUpdateDate;

    @Column(name = "board_deletedate")
    private LocalDateTime boardDeleteDate;

    @Column(name = "board_viewcount", updatable = false)
    private Integer boardViewCount;

    @Column(name = "board_isdeleted")
    private String boardIsDeleted;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "emp_id")
    private Employee empId;

    // cascade : 게시글이 삭제될 때 댓글도 삭제, orphanRemoval : 게시글이 삭제되면 댓글도 db 에서 삭제
    @OneToMany(mappedBy = "boardId", cascade = ALL, orphanRemoval = true)
    private List<CommentBoard> comments = new ArrayList<>();

    @PrePersist void boardRegDate() {
        this.boardIsDeleted = "N";
        this.boardViewCount = 0;
        // this.boardRegDate = LocalDateTime.now();
    }

    @Builder
    public Board (String boardTitle, String boardContent, Employee empId, Integer id) {
        this.id = id;
        this.boardTitle = boardTitle;
        this.boardContent = boardContent;
        this.empId = empId;
    }
}
