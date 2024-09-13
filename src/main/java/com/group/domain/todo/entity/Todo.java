package com.group.domain.todo.entity;

import com.group.domain.employee.entity.Employee;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "todo")
public class Todo {

    public Todo() {
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
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
    private LocalDateTime todoCreate;

    @Column(name = "todo_update")
    private LocalDateTime todoUpdate;

    @ManyToOne
    @JoinColumn(name = "employeeId")
    private Employee employee;
}
