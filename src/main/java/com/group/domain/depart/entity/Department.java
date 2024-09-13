package com.group.domain.depart.entity;

import com.group.domain.employee.entity.Employee;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@Entity
@Table(name = "department")
public class Department {

    public Department() {
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    @Column(name = "dept_code")
    private String deptCode;

    @Column(name = "dept_name")
    private String deptName;

    @OneToMany
    @JoinColumn(name = "empId")
    private List<Employee> employees;

}
