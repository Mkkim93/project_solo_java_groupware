package com.group.domain.board.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCommentBoard is a Querydsl query type for CommentBoard
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCommentBoard extends EntityPathBase<CommentBoard> {

    private static final long serialVersionUID = 1329733372L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCommentBoard commentBoard = new QCommentBoard("commentBoard");

    public final QBoard boardId;

    public final StringPath comContent = createString("comContent");

    public final DateTimePath<java.time.LocalDateTime> comRegDate = createDateTime("comRegDate", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> comUpdate = createDateTime("comUpdate", java.time.LocalDateTime.class);

    public final com.group.domain.hr.entity.QEmployee empId;

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public QCommentBoard(String variable) {
        this(CommentBoard.class, forVariable(variable), INITS);
    }

    public QCommentBoard(Path<? extends CommentBoard> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCommentBoard(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCommentBoard(PathMetadata metadata, PathInits inits) {
        this(CommentBoard.class, metadata, inits);
    }

    public QCommentBoard(Class<? extends CommentBoard> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.boardId = inits.isInitialized("boardId") ? new QBoard(forProperty("boardId"), inits.get("boardId")) : null;
        this.empId = inits.isInitialized("empId") ? new com.group.domain.hr.entity.QEmployee(forProperty("empId"), inits.get("empId")) : null;
    }

}

