package com.group.web.todo.api;

import com.group.application.cookie.service.CookieService;
import com.group.application.hr.dto.EmployeeDTO;
import com.group.application.hr.service.EmployeeService;
import com.group.application.todo.dto.TodoDTO;
import com.group.application.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/todo")
public class TodoApiController {

    private final TodoService todoService;
    private final CookieService cookieService;
    private final EmployeeService employeeService;

    @PostMapping("/add")
    public ResponseEntity<TodoDTO> addTodo(@RequestBody TodoDTO todoDto, Model model,
                                           @CookieValue(value = "jwtToken") String token) {
        String uuid = cookieService.getEmpUUIDFromCookiesV2(token);
        EmployeeDTO dto = employeeService.findByEmployee(uuid);
        todoDto.setEmployee(todoDto.getEmployee());
        model.addAttribute("employeeDto", dto);
        TodoDTO todoResult = todoService.todoSave(todoDto);
        return ResponseEntity.ok(todoResult);
    }

    @GetMapping("/list")
    public ResponseEntity<List<TodoDTO>> listTodo(@RequestParam(value = "employee") Integer employee,
                                                  @CookieValue(value = "jwtToken") String token, Model model) {
        String uuid = cookieService.getEmpUUIDFromCookiesV2(token);
        EmployeeDTO dto = employeeService.findByEmployee(uuid);
        model.addAttribute("employeeDto", dto);
        TodoDTO todoDto = new TodoDTO();
        todoDto.setEmployee(EmployeeDTO.builder()
                .id(employee)
                .build());
        List<TodoDTO> todoResult = todoService.findByTodoOne(todoDto);
        return ResponseEntity.ok(todoResult);
    }
}
