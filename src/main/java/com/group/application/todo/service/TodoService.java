package com.group.application.todo.service;

import com.group.application.todo.dto.TodoDTO;
import com.group.domain.todo.entity.Todo;
import com.group.domain.todo.repository.TodoRepository;
import com.group.domain.todo.repository.TodoRepositoryCustom;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepositoryCustom todoRepositoryCustom;
    private final TodoRepository todoRepository;

    // 일정 저장
    public TodoDTO todoSave(TodoDTO todoDTO) {
        return todoRepositoryCustom.todoSave(todoDTO);
    }

    // 일정 조회
    public List<TodoDTO> findByTodoList(TodoDTO todoDTO) {
        List<Todo> searchByTodoList = todoRepositoryCustom.findByTodoOfMonth(todoDTO);
        return todoDTO.setListDTO(searchByTodoList);
    }

    public TodoDTO findByTodoOne(Integer id) {
        Todo todo = todoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("no id"));
        TodoDTO todoDTO = new TodoDTO().setObjectDTO(todo);
        return todoDTO;
    }

    // 일정 수정(UPDATE)
    public TodoDTO updateByTodo(TodoDTO todoDTO) {
        return null;
    }
}
