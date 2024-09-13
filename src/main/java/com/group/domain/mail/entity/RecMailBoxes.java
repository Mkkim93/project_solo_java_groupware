package com.group.domain.mail.entity;

import com.group.domain.employee.entity.Employee;
import jakarta.persistence.*;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "recmailboxes")
public class RecMailBoxes {

    public RecMailBoxes() {
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    @Column(name = "rec_date")
    private LocalDateTime recDate;

    @ManyToOne
    @JoinColumn(name = "mailboxesId")
    private MailBoxes mailBoxes;

    @ManyToOne
    @JoinColumn(name = "employeeId")
    private Employee employee;
}
