package com.group.application.board.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.group.application.board.dto.QQnABoardDTO is a Querydsl Projection type for QnABoardDTO
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QQnABoardDTO extends ConstructorExpression<QnABoardDTO> {

    private static final long serialVersionUID = 943016204L;

    public QQnABoardDTO(com.querydsl.core.types.Expression<Integer> id, com.querydsl.core.types.Expression<Integer> boardId, com.querydsl.core.types.Expression<String> boardTitle, com.querydsl.core.types.Expression<String> boardContent, com.querydsl.core.types.Expression<String> empName, com.querydsl.core.types.Expression<String> boardPass, com.querydsl.core.types.Expression<java.time.LocalDateTime> boardRegDate, com.querydsl.core.types.Expression<Integer> boardViewCount, com.querydsl.core.types.Expression<String> isDeleted, com.querydsl.core.types.Expression<String> boardSecret) {
        super(QnABoardDTO.class, new Class<?>[]{int.class, int.class, String.class, String.class, String.class, String.class, java.time.LocalDateTime.class, int.class, String.class, String.class}, id, boardId, boardTitle, boardContent, empName, boardPass, boardRegDate, boardViewCount, isDeleted, boardSecret);
    }

}

