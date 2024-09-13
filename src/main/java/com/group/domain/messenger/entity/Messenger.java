package com.group.domain.messenger.entity;

import com.group.domain.employee.entity.Employee;
import jakarta.persistence.*;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "messenger")
public class Messenger {

    public Messenger() {
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    @Column(name = "msg_sendtime")
    private LocalDateTime msgSendTime;

    @Column(name = "msg_receivetime")
    private LocalDateTime msgReceiveTime;

    @Lob
    @Column(name = "msg_content")
    private String msgContent;

    @ManyToOne
    @JoinColumn(name = "sendId")
    private Employee msgSendId;

    @ManyToOne
    @JoinColumn(name = "receiveId")
    private Employee msgReceiveId;


}
