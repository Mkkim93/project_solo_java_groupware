package com.group.application.todo.dto;

import com.group.domain.hr.entity.Employee;
import com.group.domain.todo.entity.Todo;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class TodoDTO {

    private Integer id;
    private String todoType;
    private String todoTitle;
    private String todoContent;

    private LocalDateTime todoStartDate;
    private LocalDateTime todoEndDate;
    private LocalDateTime todoUpdate;
    private String todoStatus;

    private LocalDateTime todoCreate;

    private Integer employee; // 사원 id

    public TodoDTO setDTO(Todo todo) {
        this.id = todo.getId();
        this.todoType = todo.getTodoType();
        this.todoTitle = todo.getTodoTitle();
        this.todoContent = todo.getTodoContent();
        this.todoStatus = todo.getTodoStatus();
        this.todoStartDate = todo.getTodoStartDate();
        this.todoEndDate = todo.getTodoEndDate();
        this.employee = todo.getEmployee().getId();
        return this;
    }

    // List 엔티티 -> List DTO
    public List<TodoDTO> setListDTO(List<Todo> todos) {
        return todos.stream()
                .map(todo -> {
                    TodoDTO todoDTO = new TodoDTO();
                    todoDTO.setDTO(todo); // setDTO 메서드를 사용하여 변환
                    return todoDTO;
                })
                .collect(Collectors.toList()); // 결과를 List로 모은다
    }

    // 객체 엔티티 -> 객체 dto
    public TodoDTO toDto(Todo todo) {
        this.id = todo.getId();
        this.todoTitle = todo.getTodoTitle();
        this.todoContent = todo.getTodoContent();
        this.todoType = todo.getTodoType();
        this.todoStatus = todo.getTodoStatus();
        this.todoStartDate = todo.getTodoStartDate();
        this.todoEndDate = todo.getTodoEndDate();
        this.todoCreate = todo.getTodoCreate();
        this.todoUpdate = todo.getTodoUpdate();
        return this;
    }

    @QueryProjection
    public TodoDTO(Integer id, String todoType, String todoTitle,
                   String todoContent, LocalDateTime todoStartDate,
                   LocalDateTime todoEndDate, String todoStatus,
                   Integer employee) {
        this.id = id;
        this.todoType = todoType;
        this.todoTitle = todoTitle;
        this.todoContent = todoContent;
        this.todoStartDate = todoStartDate;
        this.todoEndDate = todoEndDate;
        this.todoStatus = todoStatus;
        this.employee = employee;
    }

}

