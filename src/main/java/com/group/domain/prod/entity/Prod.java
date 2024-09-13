package com.group.domain.prod.entity;

import com.group.domain.employee.entity.Employee;
import jakarta.persistence.*;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "prod")
public class Prod {

    public Prod() {
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    @Column(name = "prod_title")
    private String prodTitle;

    @Column(name = "prod_content")
    private String prodContent;

    @Column(name = "prod_price")
    private Integer prodPrice;

    @Column(name = "prod_category")
    private String prodCategory;

    @Column(name = "prod_create")
    private LocalDateTime prodCreate;

    @Column(name = "prod_update")
    private LocalDateTime prodUpdate;

    @ManyToOne
    @JoinColumn(name = "empId", nullable = false)
    private Employee employee;
}
