package com.group.domain.mail.entity;

import com.group.domain.hr.entity.Employee;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

import static jakarta.persistence.EnumType.*;
import static jakarta.persistence.FetchType.*;
import static jakarta.persistence.GenerationType.*;

@Entity
@Table(name = "mailtrans")
@Getter
public class MailTrans {

    @Id @GeneratedValue(strategy = IDENTITY)
    private Integer id;
    /*
        메일의 송/수신 타입 지정 (ENUM : RECEIVE, SEND)
     */
    @Enumerated(STRING)
    @Column(name = "transaction_type")
    private TransType transactionType;

    // 메일 송/수신 저장 날짜
    @Column(name = "transaction_date")
    private LocalDateTime transactionDate;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "mailbox_id")
    private MailBox mailBox;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "emp_id")
    private Employee employee;
}
