package com.group.domain.file.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFileStore is a Querydsl query type for FileStore
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFileStore extends EntityPathBase<FileStore> {

    private static final long serialVersionUID = -726091394L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFileStore fileStore = new QFileStore("fileStore");

    public final com.group.domain.board.entity.QFileBoard fileBoardId;

    public final StringPath fileName = createString("fileName");

    public final StringPath filePath = createString("filePath");

    public final DateTimePath<java.time.LocalDateTime> fileRegDate = createDateTime("fileRegDate", java.time.LocalDateTime.class);

    public final NumberPath<Long> fileSize = createNumber("fileSize", Long.class);

    public final StringPath fileType = createString("fileType");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath OriginFileName = createString("OriginFileName");

    public QFileStore(String variable) {
        this(FileStore.class, forVariable(variable), INITS);
    }

    public QFileStore(Path<? extends FileStore> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QFileStore(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QFileStore(PathMetadata metadata, PathInits inits) {
        this(FileStore.class, metadata, inits);
    }

    public QFileStore(Class<? extends FileStore> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.fileBoardId = inits.isInitialized("fileBoardId") ? new com.group.domain.board.entity.QFileBoard(forProperty("fileBoardId"), inits.get("fileBoardId")) : null;
    }

}

