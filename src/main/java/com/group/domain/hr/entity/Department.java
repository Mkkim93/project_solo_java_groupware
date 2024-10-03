package com.group.domain.hr.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.*;
import static jakarta.persistence.GenerationType.IDENTITY;


@Entity
@Table(name = "department")
@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "employees")
public class Department {

    @Id @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    @Column(name = "dept_code")
    private String deptCode;

    @Column(name = "dept_name")
    private String deptName;

    @Column(name = "dept_isdeleted")
    private String deptIsDeleted;

    @OneToMany(mappedBy = "department", fetch = LAZY)
    private List<Employee> employees = new ArrayList<>();
}
