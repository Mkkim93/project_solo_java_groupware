package com.group.domain.todo.entity;

import com.group.domain.hr.entity.Employee;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

import static jakarta.persistence.FetchType.*;

@Entity
@Table(name = "todo")
@Getter
public class Todo {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "todo_type")
    private String todoType;

    @Column(name = "todo_title")
    private String todoTitle;

    @Column(name = "todo_content")
    private String todoContent;

    @Column(name = "todo_duedate")
    private LocalDateTime todoDueDate;

    @Column(name = "todo_status")
    private String todoStatus;

    @Column(name = "todo_create")
    private LocalDateTime todoCreate; // ? 없어도 될거같음

    @Column(name = "todo_update")
    private LocalDateTime todoUpdate;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "emp_id")
    private Employee employee;
}
