package com.group.domain.board.vo;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ReplyBoardVO {

    private Integer id;
    private String rBoardTitle;
    private String rBoardContent;
    private String rBoardWriter;
    private LocalDateTime rBoardRegDate;
    private Integer boardId;
}
