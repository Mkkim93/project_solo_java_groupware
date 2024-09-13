package com.group.domain.docpayment.entity;

import com.group.domain.employee.entity.Employee;
import jakarta.persistence.*;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "approval_line")
public class Approval_Line {

    public Approval_Line() {
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    @Column(name = "approval_order")
    private Integer approvalOrder;

    @Column(name = "approval_status")
    private LocalDateTime approvalStatus;

    @Column(name = "approval_create")
    private LocalDateTime approvalCreate;

    @ManyToOne
    @JoinColumn(name = "docPaymentId")
    private DocPayment docPayId;

    @ManyToOne
    @JoinColumn(name = "employeeId")
    private Employee employee;
}
