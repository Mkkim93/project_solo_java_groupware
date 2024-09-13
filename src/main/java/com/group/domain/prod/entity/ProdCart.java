package com.group.domain.prod.entity;

import com.group.domain.employee.entity.Employee;
import jakarta.persistence.*;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "prodcart")
public class ProdCart {

    public ProdCart() {
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "prodId")
    private Prod prod;

    @ManyToOne
    @JoinColumn(name = "empId")
    private Employee employee;
}
