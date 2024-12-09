package com.group.application.board.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.group.application.board.dto.QNoticeBoardDTO is a Querydsl Projection type for NoticeBoardDTO
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QNoticeBoardDTO extends ConstructorExpression<NoticeBoardDTO> {

    private static final long serialVersionUID = 1568592802L;

    public QNoticeBoardDTO(com.querydsl.core.types.Expression<Integer> id, com.querydsl.core.types.Expression<Integer> boardId, com.querydsl.core.types.Expression<String> boardTitle, com.querydsl.core.types.Expression<String> empName, com.querydsl.core.types.Expression<String> boardContent, com.querydsl.core.types.Expression<java.time.LocalDateTime> boardRegDate, com.querydsl.core.types.Expression<Integer> boardViewCount, com.querydsl.core.types.Expression<String> isDeleted) {
        super(NoticeBoardDTO.class, new Class<?>[]{int.class, int.class, String.class, String.class, String.class, java.time.LocalDateTime.class, int.class, String.class}, id, boardId, boardTitle, empName, boardContent, boardRegDate, boardViewCount, isDeleted);
    }

}

