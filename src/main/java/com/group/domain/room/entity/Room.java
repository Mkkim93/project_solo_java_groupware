package com.group.domain.room.entity;

import com.group.domain.employee.entity.Employee;
import jakarta.persistence.*;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "room")
public class Room {

    public Room() {
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    @Column(name = "room_no")
    private Integer roomNo;

    @Column(name = "room_type")
    private String roomType;

    @Column(name = "room_title")
    private String roomTitle;

    @Column(name = "room_size")
    private Integer roomSize;

    @Column(name = "room_member")
    private String roomMember;

    @Column(name = "room_starttime")
    private LocalDateTime roomStartTime;

    @Column(name = "room_endtime")
    private LocalDateTime roomEndTime;

    @Column(name = "room_createtime")
    private LocalDateTime roomCreateTime;

    @Column(name = "room_updatetime")
    private LocalDateTime roomUpdateTime;

    @OneToOne
    @JoinColumn(name = "empId")
    private Employee employee;
}
