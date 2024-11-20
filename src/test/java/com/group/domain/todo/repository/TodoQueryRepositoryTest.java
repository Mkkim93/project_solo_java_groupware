package com.group.domain.todo.repository;

import com.group.application.todo.dto.TodoDTO;
import com.group.application.todo.service.TodoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TodoQueryRepositoryTest {

    @Autowired
    TodoQueryRepository todoQueryRepository;

    @Autowired
    TodoService todoService;

    @Test
    @DisplayName("회원의 일정 조회 단건")
    void findByOne() {

        TodoDTO todoDTO = new TodoDTO();
        todoDTO.setEmployee(1);

    }

}