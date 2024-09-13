package com.group.domain.board.entity;

import com.group.domain.employee.entity.Employee;
import jakarta.persistence.*;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "board")
public class Board {

    public Board() {
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    @Column(name = "board_title")
    private String boardTitle;

    @Column(name = "board_content")
    private String boardContent;

    @Column(name = "board_writer")
    private String boardWriter;

    @Column(name = "board_regdate")
    private LocalDateTime boardRegDate;

    @Column(name = "board_updatedate")
    private LocalDateTime boardUpdateDate;

    @Column(name = "board_deletedate")
    private LocalDateTime boardDeleteDate;

    @ManyToOne
    @JoinColumn(name = "empId")
    private Employee employee;
}
