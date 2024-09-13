package com.group.domain.mail.entity;

import com.group.domain.employee.entity.Employee;
import jakarta.persistence.*;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "mailfile")
public class MailFile {

    public MailFile() {
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    @Column(name = "mail_fname")
    private String mailFName;

    @Column(name = "mail_fpath")
    private String mailFPath;

    @Column(name = "mail_fsize")
    private Long mailFSize;

    @ManyToOne
    @JoinColumn(name = "empId")
    private Employee employee;
}
