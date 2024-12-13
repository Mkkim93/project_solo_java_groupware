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

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    public TodoDTO todoSave(TodoDTO todoDto) {
        return todoRepositoryCustom.todoSave(todoDto);
    }

    // 일정 조회 (리스트) / apiController
    public List<TodoDTO> findByTodoList(TodoDTO todoDto) {
        List<Todo> searchByTodoList = todoRepositoryCustom.findByTodoOfMonth(todoDto);
        return todoDto.setListDTO(searchByTodoList);
    }

    // 일정 조회 (리스트) / Controller
    public List<TodoDTO> findByTodoOne(TodoDTO todoDto) {
        List<TodoDTO> resultDto = todoRepositoryImpl.findByOneTodo(todoDto);
        return resultDto;
    }

    // 일정 조회 (회원 id 에 대한 리스트)
    public void updateTodo(TodoDTO todoDto) {
        Todo todo = new Todo();
        Todo todoResult = todo.setEntity(todoDto);
        todoRepository.save(todoResult);
    }

    public TodoDTO findByTodoId(Integer id) {
        TodoDTO todoDto = new TodoDTO();
        Todo todo = todoRepository.getTodoById(id);
        return todoDto.setDTO(todo);
    }

    public Integer findById(TodoDTO todoDto) {
        Todo todo = todoRepository.findById(todoDto.getId()).get();
        return todo.getId();
    }
}
