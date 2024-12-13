package com.group.domain.mail.entity;

import com.group.domain.hr.entity.Employee;
import com.group.domain.mail.entity.enums.FavoriteType;
import com.group.domain.mail.entity.enums.ReadType;
import com.group.domain.mail.entity.enums.ReceiveType;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;

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

    @Enumerated(STRING)
    @Column(name = "read_status")
    private ReadType readType;

    // 메일 송/수신 저장 날짜
    @CreatedDate
    @Column(name = "receive_date")
    private LocalDateTime receiveDate;

    @Enumerated(STRING)
    @Column(name = "receive_type")
    private ReceiveType receiveType;

    @Enumerated(STRING)
    @Column(name = "is_favorite")
    private FavoriteType isFavorite;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "mailbox_id")
    private MailBox mailBox;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "emp_id")
    private Employee employee;
}
