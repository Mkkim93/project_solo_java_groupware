package com.group.domain.mail.entity;

import com.group.domain.hr.entity.Employee;
import com.group.domain.mail.entity.enums.*;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Persistent;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.time.LocalDateTime;

import static jakarta.persistence.EnumType.*;
import static jakarta.persistence.FetchType.*;
import static jakarta.persistence.GenerationType.*;

@Entity
@Table(name = "mailtrans")
@Getter
@EnableJpaAuditing
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
public class MailTrans {

    @Id @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    @Enumerated(STRING)
    @Column(name = "read_status")
    private ReadStatus readStatue;

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

    @Enumerated(STRING)
    @Column(name = "mail_types")
    private MailTypes mailTypes;

    @Enumerated(STRING)
    @Column(name = "mailtrans_deleted")
    private IsDeleted isDeleted;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "mailbox_id")
    private MailBox mailBox;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "emp_id")
    private Employee employee;

    @PrePersist void createMailTrans() {
        this.readStatue = ReadStatus.NOREAD;
        this.isDeleted = IsDeleted.valueOf("N");
    }

    public MailTrans(Integer mailBoxId, Integer empId, String mailTypes) {
        this.mailBox = MailBox.builder()
                .id(mailBoxId)
                .build();
        this.employee = Employee.builder()
                .id(empId)
                .build();
        this.mailTypes = MailTypes.valueOf(mailTypes);
    }

    public void setReceiveMail(Integer mailBoxId, Integer receiveEmpId, String mailTypes) {
        this.mailBox = MailBox.builder()
                .id(mailBoxId)
                .build();
        this.employee = Employee.builder()
                .id(receiveEmpId)
                .build();
        this.mailTypes = MailTypes.valueOf(mailTypes);
    }

}
