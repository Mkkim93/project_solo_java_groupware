package com.group.domain.todo.repository;

import com.group.application.todo.dto.TodoDTO;
import com.group.domain.hr.entity.Employee;
import com.group.domain.todo.entity.Todo;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@RequiredArgsConstructor
public class TodoRepositoryCustom {

    private final EntityManager em;

    public TodoDTO todoSave(TodoDTO todoDTO) {
        Integer employee = todoDTO.getEmployee();
        Employee setEmpId = new Employee();
        setEmpId.setId(employee);

        Todo todo = Todo.builder()
                .todoType(todoDTO.getTodoType())
                .todoTitle(todoDTO.getTodoTitle())
                .todoContent(todoDTO.getTodoContent())
                .todoStatus(todoDTO.getTodoStatus())
                .todoStartDate(todoDTO.getTodoStartDate())
                .todoEndDate(todoDTO.getTodoEndDate())
                .employee(setEmpId)  // Employee 객체 전달
                .build();

        em.persist(todo);

        return todoDTO.setDTO(todo);
    }
}
