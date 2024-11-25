package com.group.domain.mail.entity;

import com.group.application.mail.dto.MailBoxDTO;
import com.group.domain.hr.entity.Employee;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.EnumType.*;
import static jakarta.persistence.FetchType.*;
import static jakarta.persistence.GenerationType.*;

@Entity
@Table(name = "mailbox")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MailBox {

    @Id @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    @Column(name = "mail_title")
    private String mailTitle; // 메일 제목

    @Lob
    @Column(name = "mail_content")
    private String mailContent; // 메일 내용

    @Enumerated(STRING)
    @Column(name = "mail_status")
    private MailStatus mailStatus; // 메일 읽음 상태

    @Column(name = "mail_date", updatable = false) // 메일 생성 날짜
    private LocalDateTime mailDate;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "sender_emp_id")
    private Employee senderEmployee;

    @ManyToMany(fetch = EAGER)
    @JoinTable(
            name = "mailrecvstore",
            joinColumns = @JoinColumn(name = "mailbox_id"),
            inverseJoinColumns = @JoinColumn(name = "emp_id")
    )
    private List<Employee> receiverEmployees;

    @ManyToMany
    @JoinTable(
            name = "mailstore",
            joinColumns = @JoinColumn(name = "mailbox_id"), // 현재 엔티티 mailBox 와 중간 테이블 mailstore 와의 관계 정의
            inverseJoinColumns = @JoinColumn(name = "mailfile_id") // 반대쪽 엔티티 mailFile 와 중간테이블 mailstore 와의 관계 정의
    )
    private List<MailFile> mailFiles = new ArrayList<>();

    @PrePersist void createDate() {
        this.mailDate = LocalDateTime.now();
    }

    @Builder
    public MailBox(Integer id, String mailTitle, String mailContent, LocalDateTime mailDate, Employee senderEmployee) {
       this.id = id;
       this.mailTitle = mailTitle;
       this.mailContent = mailContent;
       this.mailDate = mailDate;
       this.senderEmployee = senderEmployee;
    }
}
