package com.group.domain.todo.entity;

import com.group.application.todo.dto.TodoDTO;
import com.group.domain.hr.entity.Employee;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "todo")
@Getter @Setter
@Builder
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

    @Column(name = "todo_create")
    private LocalDateTime todoCreate; // 일정 등록일

    @Column(name = "todo_update")
    private LocalDateTime todoUpdate;

    @ManyToOne
    @JoinColumn(name = "emp_id")
    private Employee employee;

    @PrePersist void createTodo() {
        this.todoCreate = LocalDateTime.now(); // 일정을 등록한 시점
    }

    // dto 의 id를 받아 entity id로 바인딩하고 해당 컬럼의 데이터 단건을 조회하기 위한 메서드
    public Todo setDTOidConverter(Integer todoDtoId) {
        this.id = todoDtoId;
        return this;
    }

}
