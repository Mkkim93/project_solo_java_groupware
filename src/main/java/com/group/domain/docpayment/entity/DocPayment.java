package com.group.domain.docpayment.entity;

import com.group.domain.docpayment.enums.DocPayStatus;
import com.group.domain.employee.entity.Employee;
import jakarta.persistence.*;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "docpayment")
public class DocPayment {

    public DocPayment() {
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    @Column(name = "docpay_no")
    private String docPayNo;

    @Column(name = "docpay_type")
    private String docPayType;

    @Column(name = "docpay_title")
    private String docPayTitle;

    @Column(name = "docpay_content")
    private String docPayContent;

    @Column(name = "docpay_create")
    private LocalDateTime docPayCreate;

    @Column(name = "docpay_update")
    private LocalDateTime docPayUpdate;

    @Column(name = "docpay_status")
    @Enumerated(EnumType.STRING)
    private DocPayStatus docPayStatus;

    @ManyToOne
    @JoinColumn(name = "empId")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "docFileId")
    private DocPayFile docPayFile;

    @ManyToOne
    @JoinColumn(name = "approvalId")
    private Approval_Line approvalLine;
}
