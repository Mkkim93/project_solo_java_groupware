package com.group.domain.todo.repository;

import com.group.application.todo.dto.TodoDTO;
import org.springframework.data.jpa.repository.Modifying;

import java.util.List;

public interface TodoRepositoryCustom {

    void updateTodo(TodoDTO todoDTO);

    List<TodoDTO> findByOneTodo(TodoDTO todoDTO);
}
