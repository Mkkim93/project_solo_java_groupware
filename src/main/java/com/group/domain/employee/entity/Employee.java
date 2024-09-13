package com.group.domain.employee.entity;

import com.group.domain.alarm.entity.Alarm;
import com.group.domain.alarm.entity.Emp_Alarm;
import com.group.domain.attendance.entity.Attendance;
import com.group.domain.club.entity.Emp_Club;
import com.group.domain.depart.entity.Department;
import com.group.domain.docpayment.entity.Approval_Line;
import com.group.domain.docpayment.entity.DocPayment;
import com.group.domain.employee.enums.EmpIsAdmin;
import com.group.domain.employee.enums.EmpJoinYN;
import com.group.domain.market.entity.Market_Employee;
import com.group.domain.messenger.entity.Messenger;
import com.group.domain.prod.entity.Prod;
import com.group.domain.prod.entity.ProdCart;
import com.group.domain.room.entity.Room;
import com.group.domain.todo.entity.Todo;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@Entity
@Table(name = "employee")
public class Employee {

    public Employee() {
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    @Column(name = "emp_pass")
    private String empPass;

    @Column(name = "emp_name")
    private String empName;

    @Column(name = "emp_rank")
    private String empRank;

    @Column(name = "emp_regno")
    private String empRegNo;

    @Column(name = "emp_nickname")
    private String empNickname;

    @Column(name = "user_tel")
    private String userTel;

    @Column(name = "emp_email")
    private String empEmail;

    @Column(name = "user_email")
    private String userEmail;

    @Column(name = "emp_mileage")
    private Integer empMileage;

    @Column(name = "emp_joinyn")
    @Enumerated(EnumType.ORDINAL)
    private EmpJoinYN empJoinYN;

    @Column(name = "emp_isadmin")
    @Enumerated(EnumType.ORDINAL)
    private EmpIsAdmin empIsAdmin;

    // 양방향 관계 정의
    @ManyToOne
    @JoinColumn(name = "deptId")
    private Department department;

    @ManyToOne
    @JoinColumn(name = "attId")
    private Attendance attendance;

    @OneToMany(mappedBy = "employee")
    private Set<Emp_Club> empClubSet;

    @OneToMany(mappedBy = "employee")
    Set<Market_Employee> marketEmployeeSet;

    @OneToMany(mappedBy = "sender")
    private Set<Messenger> sendMessages;

    @OneToMany(mappedBy = "receiver")
    private Set<Messenger> receivedMessages;

    @OneToMany(mappedBy = "employee")
    private List<Prod> prodList;

    @OneToMany(mappedBy = "employee")
    private List<ProdCart> prodCartList;

    @OneToOne(mappedBy = "employee")
    private Room room;

    @OneToMany(mappedBy = "employee")
    private List<Todo> todoList;

    @OneToMany(mappedBy = "employee")
    private List<Emp_Alarm> empAlarmList;

    @OneToMany(mappedBy = "employee")
    private List<Approval_Line> approvalLineList;

    @OneToMany(mappedBy = "employee")
    private List<DocPayment> docPaymentList;
}
