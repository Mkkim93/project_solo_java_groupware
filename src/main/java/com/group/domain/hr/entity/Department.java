package com.group.domain.hr.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;


@Entity
@Table(name = "department")
@Getter @Setter
@NoArgsConstructor
public class Department {

    @Id @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    @Column(name = "dept_code")
    private String deptCode;

    @Column(name = "dept_name")
    private String deptName;

    @OneToMany(mappedBy = "department")
    private List<Employee> employees = new ArrayList<>();
}
