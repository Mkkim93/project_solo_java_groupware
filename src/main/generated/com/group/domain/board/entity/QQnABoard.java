package com.group.domain.board.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QQnABoard is a Querydsl query type for QnABoard
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QQnABoard extends EntityPathBase<QnABoard> {

    private static final long serialVersionUID = 1790710071L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QQnABoard qnABoard = new QQnABoard("qnABoard");

    public final QBoard board;

    public final StringPath boardPass = createString("boardPass");

    public final StringPath boardSecret = createString("boardSecret");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public QQnABoard(String variable) {
        this(QnABoard.class, forVariable(variable), INITS);
    }

    public QQnABoard(Path<? extends QnABoard> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QQnABoard(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QQnABoard(PathMetadata metadata, PathInits inits) {
        this(QnABoard.class, metadata, inits);
    }

    public QQnABoard(Class<? extends QnABoard> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.board = inits.isInitialized("board") ? new QBoard(forProperty("board"), inits.get("board")) : null;
    }

}

