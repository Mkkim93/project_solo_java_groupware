package com.group.domain.todo.repository;

import com.group.application.todo.dto.QTodoDTO;
import com.group.application.todo.dto.TodoDTO;
import com.group.domain.hr.entity.QEmployee;
import com.group.domain.todo.entity.QTodo;
import com.group.domain.todo.entity.Todo;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static com.group.domain.hr.entity.QEmployee.*;
import static com.group.domain.todo.entity.QTodo.*;

@Repository
@Transactional
public class TodoRepositoryImpl extends QuerydslRepositorySupport implements TodoRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    public TodoRepositoryImpl(EntityManager entityManager) {
        super(Todo.class);
        jpaQueryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public List<TodoDTO> findByOneTodo(TodoDTO todoDto) {

        List<TodoDTO> result = jpaQueryFactory.select(new QTodoDTO(
                        todo.id, todo.todoType, todo.todoTitle, todo.todoContent,
                        todo.todoStartDate, todo.todoEndDate, todo.todoStatus, todo.employee.id))
                .from(todo)
                .join(todo.employee, employee)
                .where(todo.employee.id.eq(todoDto.getEmployee().getId()))
                .fetch();
        return result;
    }

    @Override
    public void updateTodo(TodoDTO todoDto) {

        jpaQueryFactory.update(todo)
                .set(todo.todoTitle, existTitle(todoDto.getTodoTitle(), todoDto.getId()))
                .set(todo.todoContent, existContent(todoDto.getTodoContent(), todoDto.getId()))
                .set(todo.todoUpdate, LocalDateTime.now())
                .where(todo.id.eq(todoDto.getId()))
                .execute();
    }

    private String existContent(String todoContent, Integer todoId) {
        if (todoContent != null) {
            return todoContent;
        }
        return jpaQueryFactory.select(todo.todoContent)
                .from(todo)
                .where(todo.id.eq(todoId))
                .fetchOne();
    }

    private String existTitle(String todoTitle, Integer todoId) {
        if (todoTitle != null) {
            return todoTitle;
        }
        return jpaQueryFactory.select(todo.todoTitle)
                .from(todo)
                .where(todo.id.eq(todoId))
                .fetchOne();
    }
}
