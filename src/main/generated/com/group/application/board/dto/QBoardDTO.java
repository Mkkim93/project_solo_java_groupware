package com.group.application.board.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.group.application.board.dto.QBoardDTO is a Querydsl Projection type for BoardDTO
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QBoardDTO extends ConstructorExpression<BoardDTO> {

    private static final long serialVersionUID = -276663958L;

    public QBoardDTO(com.querydsl.core.types.Expression<Integer> id, com.querydsl.core.types.Expression<String> boardTitle, com.querydsl.core.types.Expression<String> boardContent, com.querydsl.core.types.Expression<String> empName, com.querydsl.core.types.Expression<java.time.LocalDateTime> boardRegDate, com.querydsl.core.types.Expression<Integer> boardViewCount, com.querydsl.core.types.Expression<String> isDeleted) {
        super(BoardDTO.class, new Class<?>[]{int.class, String.class, String.class, String.class, java.time.LocalDateTime.class, int.class, String.class}, id, boardTitle, boardContent, empName, boardRegDate, boardViewCount, isDeleted);
    }

}

