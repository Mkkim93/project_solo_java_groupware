package com.group.domain.board.entity;

import com.group.application.board.dto.BoardDTO;
import com.group.domain.hr.entity.Employee;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static jakarta.persistence.GenerationType.*;

@Entity
@Table(name = "board")
@Getter @NoArgsConstructor
public class Board {

    @Id @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    @Column(name = "board_title")
    private String boardTitle;

    @Column(name = "board_writer")
    private String boardWriter;

    @Lob
    @Column(name = "board_content")
    private String boardContent;

    @Column(name = "board_regdate")
    private LocalDateTime boardRegDate;

    @Column(name = "board_updatedate")
    private LocalDateTime boardUpdateDate;

    @Column(name = "board_deletedate")
    private LocalDateTime boardDeleteDate;

    @Column(name = "board_viewcount")
    private Integer boardViewCount;

    // TODO
    @OneToMany
    @JoinColumn(name = "emp_id")
    private List<Employee> employee;

}
