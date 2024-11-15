package com.group.domain.board.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFileBoard is a Querydsl query type for FileBoard
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFileBoard extends EntityPathBase<FileBoard> {

    private static final long serialVersionUID = -1570701387L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFileBoard fileBoard = new QFileBoard("fileBoard");

    public final QBoard boardId;

    public final ListPath<com.group.domain.file.entity.FileStore, com.group.domain.file.entity.QFileStore> fileStores = this.<com.group.domain.file.entity.FileStore, com.group.domain.file.entity.QFileStore>createList("fileStores", com.group.domain.file.entity.FileStore.class, com.group.domain.file.entity.QFileStore.class, PathInits.DIRECT2);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public QFileBoard(String variable) {
        this(FileBoard.class, forVariable(variable), INITS);
    }

    public QFileBoard(Path<? extends FileBoard> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QFileBoard(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QFileBoard(PathMetadata metadata, PathInits inits) {
        this(FileBoard.class, metadata, inits);
    }

    public QFileBoard(Class<? extends FileBoard> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.boardId = inits.isInitialized("boardId") ? new QBoard(forProperty("boardId"), inits.get("boardId")) : null;
    }

}

