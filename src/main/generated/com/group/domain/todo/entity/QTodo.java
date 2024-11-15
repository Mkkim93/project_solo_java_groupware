package com.group.domain.todo.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTodo is a Querydsl query type for Todo
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTodo extends EntityPathBase<Todo> {

    private static final long serialVersionUID = -1480715337L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTodo todo = new QTodo("todo");

    public final com.group.domain.hr.entity.QEmployee employee;

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath todoContent = createString("todoContent");

    public final DateTimePath<java.time.LocalDateTime> todoCreate = createDateTime("todoCreate", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> todoEndDate = createDateTime("todoEndDate", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> todoStartDate = createDateTime("todoStartDate", java.time.LocalDateTime.class);

    public final StringPath todoStatus = createString("todoStatus");

    public final StringPath todoTitle = createString("todoTitle");

    public final StringPath todoType = createString("todoType");

    public final DateTimePath<java.time.LocalDateTime> todoUpdate = createDateTime("todoUpdate", java.time.LocalDateTime.class);

    public QTodo(String variable) {
        this(Todo.class, forVariable(variable), INITS);
    }

    public QTodo(Path<? extends Todo> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTodo(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTodo(PathMetadata metadata, PathInits inits) {
        this(Todo.class, metadata, inits);
    }

    public QTodo(Class<? extends Todo> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.employee = inits.isInitialized("employee") ? new com.group.domain.hr.entity.QEmployee(forProperty("employee"), inits.get("employee")) : null;
    }

}

