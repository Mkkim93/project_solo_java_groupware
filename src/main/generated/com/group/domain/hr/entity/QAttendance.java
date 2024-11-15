package com.group.domain.hr.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAttendance is a Querydsl query type for Attendance
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAttendance extends EntityPathBase<Attendance> {

    private static final long serialVersionUID = -1198560034L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAttendance attendance = new QAttendance("attendance");

    public final DateTimePath<java.time.LocalDateTime> attCreate = createDateTime("attCreate", java.time.LocalDateTime.class);

    public final DatePath<java.time.LocalDate> attDate = createDate("attDate", java.time.LocalDate.class);

    public final NumberPath<Long> attDuration = createNumber("attDuration", Long.class);

    public final NumberPath<Long> attLeave = createNumber("attLeave", Long.class);

    public final DateTimePath<java.time.LocalDateTime> attOff = createDateTime("attOff", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> attOn = createDateTime("attOn", java.time.LocalDateTime.class);

    public final NumberPath<Long> attOverDuration = createNumber("attOverDuration", Long.class);

    public final NumberPath<Long> attPerception = createNumber("attPerception", Long.class);

    public final NumberPath<Long> attVacation = createNumber("attVacation", Long.class);

    public final QEmployee employee;

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public QAttendance(String variable) {
        this(Attendance.class, forVariable(variable), INITS);
    }

    public QAttendance(Path<? extends Attendance> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAttendance(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAttendance(PathMetadata metadata, PathInits inits) {
        this(Attendance.class, metadata, inits);
    }

    public QAttendance(Class<? extends Attendance> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.employee = inits.isInitialized("employee") ? new QEmployee(forProperty("employee"), inits.get("employee")) : null;
    }

}

