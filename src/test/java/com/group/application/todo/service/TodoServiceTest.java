package com.group.application.todo.service;

import com.group.application.hr.dto.EmployeeDTO;
import com.group.application.todo.dto.TodoDTO;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
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
    @DisplayName("서비스에서 일정 저장")
    void save() {
        LocalDateTime now = LocalDateTime.now();
        TodoDTO todoDto = new TodoDTO();
        EmployeeDTO empDto = new EmployeeDTO();
        empDto.setId(29);
        todoDto.setTodoTitle("일정 저장 테스트2 (서버)");
        todoDto.setTodoContent("테스트2 (서버)");
        todoDto.setTodoStartDate(now);
        todoDto.setTodoEndDate(now);
        todoDto.setEmployee(empDto);
        todoService.todoSave(todoDto);
    }

    @Test
    @DisplayName("일정 수정 todo id 조회")
    void findByTodoId() {
        Integer id = 22;
        TodoDTO byTodoId = todoService.findByTodoId(id);
        log.info("byTodoId.getId={}", byTodoId.getId());
        Assertions.assertThat(byTodoId.getId()).isEqualTo(id);
    }

}