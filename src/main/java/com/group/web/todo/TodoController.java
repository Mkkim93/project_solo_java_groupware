package com.group.web.todo;

import com.group.application.cookie.service.CookieService;
import com.group.application.hr.dto.EmployeeDTO;
import com.group.application.hr.service.EmployeeService;
import com.group.application.todo.dto.TodoDTO;
import com.group.application.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/todo")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;
    private final EmployeeService employeeService;
    private final CookieService cookieService;

    @GetMapping("/detail")
    public String showCalendar(@CookieValue(value = "jwtToken") String token, Model model, TodoDTO todoDto) {
        String uuid = cookieService.getEmpUUIDFromCookiesV2(token);
        EmployeeDTO dto = employeeService.findByEmployee(uuid);
        todoDto.setEmployee(dto);
        model.addAttribute("todoDto", todoService.findByTodoOne(todoDto));
        model.addAttribute("employeeDto", dto);
        return "/todo/detail";
    }

    @GetMapping("/readonly")
    public String readOnlyCalender(@CookieValue(value = "jwtToken") String token,
                                   Model model, TodoDTO todoDto) {
        String uuid = cookieService.getEmpUUIDFromCookiesV2(token);
        EmployeeDTO dto = employeeService.findByEmployee(uuid);
        todoDto.setEmployee(dto);
        model.addAttribute("todoDto", todoService.findByTodoOne(todoDto));
        model.addAttribute("employeeDto", dto);
        return "todo/readonly";
    }


    @GetMapping("/modify/{id}")
    public String modify(@PathVariable("id") Integer id,
                             Model model) {
        TodoDTO todoDto = todoService.findByTodoId(id);
        model.addAttribute("todoDto", todoDto);
        return "/todo/modify";
    }

    @PostMapping("/modify/update/{id}")
    public String todoUpdateWriting(@PathVariable("id") Integer id,
                                    @ModelAttribute TodoDTO todoDto,
                                    @CookieValue(value = "jwtToken") String token, Model model) {
        String uuid = cookieService.getEmpUUIDFromCookiesV2(token);
        EmployeeDTO dto = employeeService.findByEmployee(uuid);
        model.addAttribute("employeeDto", dto);
        TodoDTO todoTemp = todoService.findByTodoId(id);
        todoTemp.setEmployee(dto);
        todoTemp.setTodoType(todoDto.getTodoType());
        todoTemp.setTodoTitle(todoDto.getTodoTitle());
        todoTemp.setTodoContent(todoDto.getTodoContent());
        todoTemp.setTodoStartDate(todoDto.getTodoStartDate());
        todoTemp.setTodoEndDate(todoDto.getTodoEndDate());
        todoTemp.setTodoUpdate(LocalDateTime.now());

        todoService.updateTodo(todoTemp);

        return "redirect:/todo/detail";
    }
}
