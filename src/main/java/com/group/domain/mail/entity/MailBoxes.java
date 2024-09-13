package com.group.domain.mail.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Set;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "mailboxes")
public class MailBoxes {

    public MailBoxes() {
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    @Column(name = "mail_title")
    private String mailTitle;

    @Column(name = "mail_content")
    private String mailContent;

    @Column(name = "mail_status")
    private String mailStatus;

    @Column(name = "mail_date")
    private LocalDateTime mailDate;

    @ManyToOne
    @JoinColumn(name = "mailFileId")
    private MailFile mailFile;

    // 양방향 mapping
    @OneToMany(mappedBy = "mailboxes")
    private Set<SendMailBoxes> sendMailBoxesSet;

    @OneToMany(mappedBy = "mailboxes")
    private Set<RecMailBoxes> recMailBoxesSet;
}
