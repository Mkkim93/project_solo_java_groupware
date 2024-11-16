package com.group.domain.todo.repository;

import com.group.application.todo.dto.TodoDTO;
import com.group.application.todo.service.TodoService;
import com.group.domain.todo.entity.Todo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Slf4j
@SpringBootTest
class TodoRepositoryCustomTest {

    @Autowired
    TodoRepositoryCustom todoRepositoryCustom;

    @Autowired
    TodoService todoService;

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

    @Test
    @DisplayName("월 단위 일정 조회하기")
    void todoSearchByMonth() {
        TodoDTO todoDTO = new TodoDTO();
        todoDTO.setEmployee(1);
        List<Todo> byTodoOfMonth = todoRepositoryCustom.findByTodoOfMonth(todoDTO);
        for (Todo todo : byTodoOfMonth) {
            System.out.println("todo.getId() = " + todo.getId());
            System.out.println("todo.getTodoType() = " + todo.getTodoType());
            System.out.println("todo.getEmployee() = " + todo.getEmployee().getId());
            System.out.println("todo.getTodoTitle() = " + todo.getTodoTitle());
        }
    }

    @Test
    @DisplayName("캘린더 데이터 조회 (내부 서비스 레이어로 테스트 수정)")
    void todoSearchByMonthV2() {
        TodoDTO todoDTO = new TodoDTO();
        todoDTO.setEmployee(1);
        List<TodoDTO> todoResult = todoService.findByTodoList(todoDTO);
        // dto 조회
        for (TodoDTO dto : todoResult) {
            System.out.println("dto.getTodoCreate() = " + dto.getTodoCreate());
            System.out.println("dto.getTodoContent() = " + dto.getTodoContent());
            System.out.println("dto.getId() = " + dto.getId());
            System.out.println("dto.getEmployee() = " + dto.getEmployee());
        }
    }

    @Test
    @DisplayName("일정 조회 (단건)")
    void todoSearchByFindOne() {
        TodoDTO todoDTO = new TodoDTO();
        todoDTO.setId(1);
        todoDTO.setEmployee(1); // todo null 나옴;;
        TodoDTO byTodoOne = todoService.findByTodoOne(1);
        System.out.println("byTodoOne.getId() = " + byTodoOne.getId());
        System.out.println("byTodoOne.getEmployee() = " + byTodoOne.getEmployee());
        System.out.println("byTodoOne.getTodoTitle() = " + byTodoOne.getTodoTitle());
        System.out.println("byTodoOne.getTodoContent() = " + byTodoOne.getTodoContent());
    }
}