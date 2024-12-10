package com.group.domain.todo.repository;

import com.group.application.todo.dto.TodoDTO;
import com.group.domain.hr.entity.Employee;
import com.group.domain.todo.entity.Todo;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
@RequiredArgsConstructor
public class TodoQueryRepository {

    private final EntityManager em;

    public TodoDTO todoSave(TodoDTO todoDto) {
        Integer employee = todoDto.getEmployee().getId();
        Employee setEmpId = new Employee();
        setEmpId.setId(employee);
        Todo todo = Todo.builder()
                .id(todoDto.getId())
                .todoType(todoDto.getTodoType())
                .todoTitle(todoDto.getTodoTitle())
                .todoContent(todoDto.getTodoContent())
                .todoStatus(todoDto.getTodoStatus())
                .todoStartDate(todoDto.getTodoStartDate())
                .todoEndDate(todoDto.getTodoEndDate())
                .employee(setEmpId)  // Employee 객체 전달
                .build();
        em.persist(todo);
        return todoDto.setDTO(todo);
    }

    public List<Todo> findByTodoOfMonth(TodoDTO todoDto) {
        Integer employee = todoDto.getEmployee().getId();
        Employee setEmpId = new Employee();
        setEmpId.setId(employee);
        Todo todo = Todo.builder()
                .id(todoDto.getId())
                .todoType(todoDto.getTodoType())
                .todoTitle(todoDto.getTodoTitle())
                .todoContent(todoDto.getTodoContent())
                .todoStatus(todoDto.getTodoStatus())
                .todoStartDate(todoDto.getTodoStartDate())
                .todoEndDate(todoDto.getTodoEndDate())
                .employee(setEmpId)  // Employee 객체 전달
                .build();

        List resultList = em.createQuery("select t from Todo t where employee = :employee")
                .setParameter("employee", todo.getEmployee())
                .getResultList();
        em.flush();

        return resultList;
    }
}
