package com.group.domain.mail.entity;

import com.group.domain.hr.entity.Employee;
import com.group.domain.mail.entity.enums.IsDeleted;
import com.group.domain.mail.entity.enums.MailStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.time.LocalDateTime;
import java.util.List;

import static jakarta.persistence.EnumType.*;
import static jakarta.persistence.FetchType.*;
import static jakarta.persistence.GenerationType.*;

@Entity
@Table(name = "mailbox")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EnableJpaAuditing
@EntityListeners(AuditingEntityListener.class)
public class MailBox {

    @Id @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    @Column(name = "mail_title")
    private String mailTitle; // 메일 제목

    @Lob
    @Column(name = "mail_content")
    private String mailContent; // 메일 내용

    @CreatedDate
    @Column(name = "mail_date", updatable = false) // 메일 생성 날짜
    private LocalDateTime mailDate;

    @Enumerated(value = STRING)
    @Column(name = "mail_status")
    private MailStatus mailStatus;

    @Column(name = "mail_deleted")
    private String mailDeleted;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "sender_emp_id")
    private Employee senderEmployee;

    @PrePersist
    void setMailBox() {
        this.mailDeleted = "N";
    }

   /* @Enumerated(STRING)
    @Column(name = "mail_sendType")
    private MailSendType mailSendType;*/

    @ManyToMany(fetch = LAZY)
    @JoinTable(
            name = "mailrecvstore",
            joinColumns = @JoinColumn(name = "mailbox_id"),
            inverseJoinColumns = @JoinColumn(name = "emp_id")
    )
    private List<Employee> receiverEmployees;

    @Builder
    public MailBox(Integer id, String mailTitle, String mailContent,
                   LocalDateTime mailDate, Employee senderEmployee, MailStatus mailStatus) {
       this.id = id;
       this.mailTitle = mailTitle;
       this.mailContent = mailContent;
       this.mailDate = mailDate;
       this.senderEmployee = senderEmployee;
       this.mailStatus = mailStatus;

    }
}
