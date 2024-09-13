package com.group.domain.board.vo;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BoardVO {

    private Integer id;
    private String boardTitle;
    private String boardContent;
    private String boardWriter;
    private LocalDateTime boardRegDate;
    private LocalDateTime boardUpdateDate;
    private LocalDateTime boardDeleteDate;
    private Integer emp_Id;
}
