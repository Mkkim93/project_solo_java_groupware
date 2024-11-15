package com.group.domain.todo.repository;

import com.group.application.todo.dto.TodoDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class TodoRepositoryCustomTest {

    @Autowired
    TodoRepositoryCustom todoRepositoryCustom;

    @Test
    void todoSave() {
        TodoDTO todoDTO = new TodoDTO();
        todoDTO.setEmployee(1);
        todoDTO.setTodoType("회의");
        todoDTO.setTodoTitle("개발 회의2");
        todoDTO.setTodoContent("회의 내용 테스트2");
        todoDTO.setTodoStatus("o");
        todoRepositoryCustom.todoSave(todoDTO);
    }
}