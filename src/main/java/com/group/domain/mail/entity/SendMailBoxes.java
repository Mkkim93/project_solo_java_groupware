package com.group.domain.mail.entity;

import com.group.domain.employee.entity.Employee;
import jakarta.persistence.*;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "sendmailboxes")
public class SendMailBoxes {

    public SendMailBoxes() {
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    @Column(name = "send_date")
    private LocalDateTime sendDate;

    @ManyToOne
    @JoinColumn(name = "mailboxesId")
    private MailBoxes mailBoxes;

    @ManyToOne
    @JoinColumn(name = "employeeId")
    private Employee employee;

}
