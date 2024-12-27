package com.group.web.todo.api;

import com.group.application.hr.dto.EmployeeDTO;
import com.group.application.hr.service.EmployeeService;
import com.group.application.todo.dto.TodoDTO;
import com.group.application.todo.service.TodoService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/todo")
public class TodoApiController {

    private final TodoService todoService;
    private final EmployeeService employeeService;

    @PostMapping("/add")
    public ResponseEntity<TodoDTO> addTodo(@RequestBody TodoDTO todoDto, Model model, EmployeeDTO employeeDto,
                                           @CookieValue(value = "uuid") String empUUID) {
        employeeDto.setEmpUUID(empUUID);
        todoDto.setEmployee(todoDto.getEmployee());
        model.addAttribute("employeeDto", employeeService.findByEmployee(employeeDto));
        TodoDTO todoResult = todoService.todoSave(todoDto);
        return ResponseEntity.ok(todoResult);
    }

    @GetMapping("/list")
    public ResponseEntity<List<TodoDTO>> listTodo(@RequestParam(value = "employee") Integer employee, EmployeeDTO employeeDto,
                                                  @CookieValue(value = "uuid") String empUUID, Model model) {
        employeeDto.setEmpUUID(empUUID);
        model.addAttribute("employeeDto", employeeService.findByEmployee(employeeDto));

        TodoDTO todoDto = new TodoDTO();

        todoDto.setEmployee(EmployeeDTO.builder()
                .id(employee)
                .build());

        List<TodoDTO> todoResult = todoService.findByTodoOne(todoDto);
        return ResponseEntity.ok(todoResult);
    }

    @PostMapping("/delete/todos/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable("id") Integer todoId) {
        Boolean isDeleted = todoService.deleteTodo(todoId);
        if (isDeleted) {
            return ResponseEntity.noContent().build(); // status code : 204
        } else {
            return ResponseEntity.notFound().build(); // status code : 404
        }
    }
}
