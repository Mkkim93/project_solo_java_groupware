package com.group.domain.todo.repository;

import com.group.application.hr.dto.EmployeeDTO;
import com.group.application.todo.dto.TodoDTO;
import com.group.application.todo.service.TodoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

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
        TodoDTO dto = new TodoDTO();
        EmployeeDTO empDto = new EmployeeDTO();
        empDto.setId(29);
        dto.setEmployee(empDto);
        List<TodoDTO> result = todoService.findByTodoOne(dto);
        result.stream().forEach(System.out::println);
    }

    @Test
    @DisplayName("일정 저장")
    void save() {
        LocalDateTime now = LocalDateTime.now();
        TodoDTO todoDto = new TodoDTO();
        EmployeeDTO empDto = new EmployeeDTO();
        empDto.setId(29);
        todoDto.setTodoTitle("일정 저장 테스트3 (서버)");
        todoDto.setTodoContent("테스트3 (서버)");
        todoDto.setTodoStartDate(now);
        todoDto.setTodoEndDate(now);
        todoDto.setEmployee(empDto);
        todoQueryRepository.todoSave(todoDto);
    }

}