package com.group.application.todo.service;

import com.group.application.todo.dto.TodoDTO;
import com.group.domain.todo.entity.Todo;
import com.group.domain.todo.repository.TodoRepository;
import com.group.domain.todo.repository.TodoQueryRepository;
import com.group.domain.todo.repository.TodoRepositoryImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepositoryImpl todoRepositoryImpl;
    private final TodoQueryRepository todoRepositoryCustom;
    private final TodoRepository todoRepository;

    // 일정 저장
    public TodoDTO todoSave(TodoDTO todoDTO) {
        return todoRepositoryCustom.todoSave(todoDTO);
    }

    // 일정 조회 (리스트) / apiController
    public List<TodoDTO> findByTodoList(TodoDTO todoDTO) {
        List<Todo> searchByTodoList = todoRepositoryCustom.findByTodoOfMonth(todoDTO);
        return todoDTO.setListDTO(searchByTodoList);
    }

    // 일정 조회 (리스트) / Controller
    public List<TodoDTO> findByTodoOne(TodoDTO todoDTO) {
        return todoRepositoryImpl.findByOneTodo(todoDTO);
    }

    // 일정 조회 (회원 id 에 대한 리스트)
    public void updateTodo(TodoDTO todoDTO) {
        Todo todo = new Todo();
        Todo todoResult = todo.setEntity(todoDTO);
        todoRepository.save(todoResult);
    }

    // TODO
    public TodoDTO findByTodoId(Integer id) {
        TodoDTO todoDTO = new TodoDTO();
        Todo todo = todoRepository.getTodoById(id);
        return todoDTO.setDTO(todo);
    }

    public Integer findById(TodoDTO todoDTO) {
        Todo todo = todoRepository.findById(todoDTO.getId()).get();
        return todo.getId();
    }
}
