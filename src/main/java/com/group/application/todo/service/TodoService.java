package com.group.application.todo.service;

import com.group.application.todo.dto.TodoDTO;
import com.group.domain.todo.entity.Todo;
import com.group.domain.todo.repository.TodoRepositoryCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepositoryCustom todoRepositoryCustom;

    public TodoDTO TodoSave(TodoDTO todoDTO) {
        return todoRepositoryCustom.todoSave(todoDTO);
    }
}
