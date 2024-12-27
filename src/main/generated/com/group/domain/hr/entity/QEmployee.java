package com.group.domain.hr.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QEmployee is a Querydsl query type for Employee
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QEmployee extends EntityPathBase<Employee> {

    private static final long serialVersionUID = 1936615907L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QEmployee employee = new QEmployee("employee");

    public final QDepartment department;

    public final StringPath empEmail = createString("empEmail");

    public final DateTimePath<java.time.LocalDateTime> empHireDate = createDateTime("empHireDate", java.time.LocalDateTime.class);

    public final StringPath empImg = createString("empImg");

    public final StringPath empJoinYN = createString("empJoinYN");

    public final StringPath empName = createString("empName");

    public final StringPath empNo = createString("empNo");

    public final StringPath empPass = createString("empPass");

    public final StringPath empRank = createString("empRank");

    public final StringPath empRegNo = createString("empRegNo");

    public final StringPath empTel = createString("empTel");

    public final DateTimePath<java.time.LocalDateTime> empTerminationDate = createDateTime("empTerminationDate", java.time.LocalDateTime.class);

    public final StringPath empUUID = createString("empUUID");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final ListPath<com.group.domain.mail.entity.MailBox, com.group.domain.mail.entity.QMailBox> receivedMailBoxes = this.<com.group.domain.mail.entity.MailBox, com.group.domain.mail.entity.QMailBox>createList("receivedMailBoxes", com.group.domain.mail.entity.MailBox.class, com.group.domain.mail.entity.QMailBox.class, PathInits.DIRECT2);

    public final StringPath roleType = createString("roleType");

    public final ListPath<com.group.domain.mail.entity.MailBox, com.group.domain.mail.entity.QMailBox> sentMailBoxes = this.<com.group.domain.mail.entity.MailBox, com.group.domain.mail.entity.QMailBox>createList("sentMailBoxes", com.group.domain.mail.entity.MailBox.class, com.group.domain.mail.entity.QMailBox.class, PathInits.DIRECT2);

    public final StringPath userEmail = createString("userEmail");

    public final StringPath userTel = createString("userTel");

    public QEmployee(String variable) {
        this(Employee.class, forVariable(variable), INITS);
    }

    public QEmployee(Path<? extends Employee> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QEmployee(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QEmployee(PathMetadata metadata, PathInits inits) {
        this(Employee.class, metadata, inits);
    }

    public QEmployee(Class<? extends Employee> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.department = inits.isInitialized("department") ? new QDepartment(forProperty("department")) : null;
    }

}

