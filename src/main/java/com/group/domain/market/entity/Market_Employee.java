package com.group.domain.market.entity;

import com.group.domain.employee.entity.Employee;
import jakarta.persistence.*;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "market_employee")
public class Market_Employee {

    public Market_Employee() {
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "marketId")
    private Market marketId;

    @ManyToOne
    @JoinColumn(name = "empId")
    private Employee empId;
}
