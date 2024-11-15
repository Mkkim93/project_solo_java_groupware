package com.group.domain.board.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBoard is a Querydsl query type for Board
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBoard extends EntityPathBase<Board> {

    private static final long serialVersionUID = 1075905873L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBoard board = new QBoard("board");

    public final StringPath boardContent = createString("boardContent");

    public final DateTimePath<java.time.LocalDateTime> boardDeleteDate = createDateTime("boardDeleteDate", java.time.LocalDateTime.class);

    public final StringPath boardIsDeleted = createString("boardIsDeleted");

    public final DateTimePath<java.time.LocalDateTime> boardRegDate = createDateTime("boardRegDate", java.time.LocalDateTime.class);

    public final StringPath boardTitle = createString("boardTitle");

    public final DateTimePath<java.time.LocalDateTime> boardUpdateDate = createDateTime("boardUpdateDate", java.time.LocalDateTime.class);

    public final NumberPath<Integer> boardViewCount = createNumber("boardViewCount", Integer.class);

    public final ListPath<CommentBoard, QCommentBoard> comments = this.<CommentBoard, QCommentBoard>createList("comments", CommentBoard.class, QCommentBoard.class, PathInits.DIRECT2);

    public final com.group.domain.hr.entity.QEmployee empId;

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public QBoard(String variable) {
        this(Board.class, forVariable(variable), INITS);
    }

    public QBoard(Path<? extends Board> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBoard(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBoard(PathMetadata metadata, PathInits inits) {
        this(Board.class, metadata, inits);
    }

    public QBoard(Class<? extends Board> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.empId = inits.isInitialized("empId") ? new com.group.domain.hr.entity.QEmployee(forProperty("empId"), inits.get("empId")) : null;
    }

}

