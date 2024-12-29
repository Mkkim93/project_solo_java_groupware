package com.group.domain.mail.entity;

import com.group.domain.hr.entity.Employee;
import com.group.domain.mail.entity.enums.ISCC;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.EnumType.*;

@Entity
@Table(name = "mailrecvstore")
@Getter
@NoArgsConstructor
public class MailRecvStore {

    @EmbeddedId
    private MailRecvStoreId id;

    @ManyToOne
    @MapsId("mailboxId")
    @JoinColumn(name = "mailbox_id")
    private MailBox mailBox;

    @ManyToOne
    @MapsId("empId")
    @JoinColumn(name = "emp_id")
    private Employee employee;

    @Enumerated(STRING)
    @Column(name = "is_cc")
    private ISCC iscc;
}


