package com.group.application.todo.service;

import com.group.application.todo.dto.TodoDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class TodoServiceTest {

    @Autowired
    TodoService todoService;

    @Test
    @DisplayName("일정 수정 테스트")
    void update() {
        TodoDTO todoDTO = new TodoDTO();
        todoDTO.setEmployee(1);
        todoDTO.setId(3);

        todoDTO.setTodoTitle("일정 수정 테스트 제목(title)");
        todoDTO.setTodoContent("일정 수정 테스트 내용(content)");

        todoService.updateTodo(todoDTO);
    }

    @Test
    @DisplayName("일정 수정 테스트2 (제목만 수정)")
    void updateTitle() {
        TodoDTO todoDTO = new TodoDTO();
        todoDTO.setEmployee(1);
        todoDTO.setId(3);

        todoDTO.setTodoTitle("일정 제목만 수정 테스트2");
        todoService.updateTodo(todoDTO);
    }

    @Test
    @DisplayName("일정 수정 테스트3 (내용만 수정)")
    void updateContent() {
        TodoDTO todoDTO = new TodoDTO();
        todoDTO.setEmployee(1);
        todoDTO.setId(3);

        todoDTO.setTodoContent("일정 내용만 수정 테스트3.1");
        todoService.updateTodo(todoDTO);
    }

    @Test
    @DisplayName("일정 수정 테스트4 (내용 제목 입력 안할 시 기존 데이터를 그대로 반영)")
    void updateEmptyDate() {
        TodoDTO todoDTO = new TodoDTO();
        todoDTO.setEmployee(1);
        todoDTO.setId(3);

        todoService.updateTodo(todoDTO);
    }

    @Test
    @DisplayName("일정 조회 (단건)")
    void findByOne() {
        TodoDTO todoDTO = new TodoDTO();
        todoDTO.setEmployee(1);
        todoDTO.setId(3);

    }

}