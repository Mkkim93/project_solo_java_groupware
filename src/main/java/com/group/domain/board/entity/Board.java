package com.group.domain.board.entity;

import com.group.domain.hr.entity.Employee;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    @CreatedDate
    @Column(name = "board_regdate", updatable = false)
    private LocalDateTime boardRegDate;

    @LastModifiedDate
    @Column(name = "board_updatedate")
    private LocalDateTime boardUpdateDate;

    @Column(name = "board_deletedate")
    private LocalDateTime boardDeleteDate;

    @Column(name = "board_viewcount", updatable = false)
    private Integer boardViewCount;

    @Column(name = "board_isdeleted", updatable = false)
    private String boardIsDeleted;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "emp_id")
    private Employee employee;

    // cascade : 게시글이 삭제될 때 댓글도 삭제, orphanRemoval : 게시글이 삭제되면 댓글도 db 에서 삭제
    @OneToMany(mappedBy = "board", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = false)
    private List<CommentBoard> comments = new ArrayList<>();

    @PrePersist void boardRegDate() {
        this.boardIsDeleted = "N";
        this.boardViewCount = 0;
    }

    @Builder
    public Board(String boardTitle, String boardContent, Employee employee, Integer id, LocalDateTime boardRegDate) {
        this.id = id;
        this.boardTitle = boardTitle;
        this.boardContent = boardContent;
        this.employee = employee;
        this.boardRegDate = boardRegDate;
    }
}
