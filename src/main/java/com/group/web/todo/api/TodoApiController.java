package com.group.web.todo.api;

import com.group.application.todo.dto.TodoDTO;
import com.group.application.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/todo")
public class TodoApiController {

    private final TodoService todoService;

    @PostMapping("/add")
    public ResponseEntity<TodoDTO> addTodo(@RequestBody TodoDTO todoDTO) {
        TodoDTO todoResult = todoService.todoSave(todoDTO);
        return ResponseEntity.ok(todoResult);
    }

    @GetMapping("/list")
    public ResponseEntity<List<TodoDTO>> listTodo(@RequestParam(value = "employee") Integer employee) {
        TodoDTO todoDTO = new TodoDTO();
        todoDTO.setEmployee(employee);
        List<TodoDTO> todoResult = todoService.findByTodoList(todoDTO);
        return ResponseEntity.ok(todoResult);
    }

}
