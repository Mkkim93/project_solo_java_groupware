package com.group.domain.board.entity;

import com.group.application.board.dto.CommentDTO;
import com.group.domain.hr.entity.Employee;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.print.attribute.standard.MediaSize;
import java.time.LocalDateTime;

import static jakarta.persistence.FetchType.*;
import static jakarta.persistence.GenerationType.*;

@Entity
@Table(name = "commentboard")
@Getter @Setter
@EntityListeners(AuditingEntityListener.class)
@EnableJpaAuditing
@NoArgsConstructor
public class CommentBoard {

    @Id @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    @Column(name = "com_content")
    private String comContent;

    @CreatedDate
    @Column(name = "com_regdate", updatable = false)
    private LocalDateTime comRegDate;

    @LastModifiedDate
    @Column(name = "com_update")
    private LocalDateTime comUpdate;

    @Column(name = "com_isdeleted")
    private String comIsDeleted;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "emp_id")
    private Employee employee;

    @PrePersist
    void createComment() {
        this.comIsDeleted = "N";
    }

    @Builder
    public CommentBoard(CommentDTO dto) {
        this.id = dto.getId();
        this.comContent = dto.getComContent();
        this.employee = Employee.builder()
                .id(dto.getEmpId())
                .build();
        this.board = Board.builder()
                .id(dto.getBoardId())
                .build();
    }
}
