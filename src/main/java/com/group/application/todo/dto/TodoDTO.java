package com.group.application.todo.dto;

import com.group.domain.todo.entity.Todo;
import lombok.Data;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Data
public class TodoDTO {

    private String todoType;
    private String todoTitle;
    private String todoContent;

    private LocalDateTime todoStartDate;
    private LocalDateTime todoEndDate;
    private String todoStatus;

    private LocalDateTime todoCreate;

    private Integer employee; // 사원 id

    public TodoDTO setDTO(Todo todo) {
        this.todoType = todo.getTodoType();
        this.todoTitle = todo.getTodoTitle();
        this.todoContent = todo.getTodoContent();
        this.todoStatus = todo.getTodoStatus();
        this.todoStartDate = todo.getTodoStartDate();
        this.todoEndDate = todo.getTodoEndDate();
        this.employee = todo.getEmployee().getId();
        return this;
    }
}
