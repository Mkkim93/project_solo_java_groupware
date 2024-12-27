package com.group.domain.todo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.group.application.todo.dto.TodoDTO;
import com.group.domain.hr.entity.Employee;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.cglib.core.Local;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Entity
@Table(name = "todo")
@Getter @Setter
@Builder
@EntityListeners(AuditingEntityListener.class)
@EnableJpaAuditing
@AllArgsConstructor
@NoArgsConstructor
public class Todo {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "todo_type")
    private String todoType;

    @Column(name = "todo_title")
    private String todoTitle;

    @Column(name = "todo_content")
    private String todoContent;

    @Column(name = "todo_startdate")
    private LocalDateTime todoStartDate; // 일정 시작일

    @Column(name = "todo_enddate")
    private LocalDateTime todoEndDate; // 일정 종료일

    @Column(name = "todo_status")
    private String todoStatus; // 일정 상태

    @CreatedDate
    @Column(name = "todo_create", updatable = false)
    private LocalDateTime todoCreate; // 일정 등록일

    @LastModifiedDate
    @Column(name = "todo_update")
    private LocalDateTime todoUpdate;

    @Column(name = "todo_deleted")
    private String todoDeleted;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "emp_id")
    private Employee employee;

    @PrePersist void createTodo() {
        this.todoDeleted = "N";
    }

    public Todo setEntity(TodoDTO todoDTO) {
        this.id = todoDTO.getId();
        this.todoType = todoDTO.getTodoType();
        this.todoTitle = todoDTO.getTodoTitle();
        this.todoContent = todoDTO.getTodoContent();
        this.todoStartDate = todoDTO.getTodoStartDate();
        this.todoEndDate = todoDTO.getTodoEndDate();
        this.todoUpdate = todoDTO.getTodoUpdate();
        this.employee = Employee.builder()
                .id(todoDTO.getEmployee().getId())
                .build();
        return this;
    }

}
