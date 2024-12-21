package com.group.web.todo;

import com.group.application.hr.dto.EmployeeDTO;
import com.group.application.hr.service.EmployeeService;
import com.group.application.todo.dto.TodoDTO;
import com.group.application.todo.service.TodoService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Slf4j
@Controller
@RequestMapping("/todo")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;
    private final EmployeeService employeeService;


    @GetMapping("/detail")
    public String showCalendar(EmployeeDTO employeeDto, Model model, TodoDTO todoDto, @CookieValue(value = "uuid") String empUUID) {
        employeeDto.setEmpUUID(empUUID);
        EmployeeDTO dto = employeeService.findByEmployee(employeeDto);
        todoDto.setEmployee(dto);
        model.addAttribute("todoDto", todoService.findByTodoOne(todoDto));
        model.addAttribute("employeeDto", dto);
        return "/todo/detail";
    }

    @GetMapping("/readonly")
    public String readOnlyCalender(Model model, TodoDTO todoDto, EmployeeDTO employeeDto,
                                   @CookieValue(value = "uuid") String empUUID) {
        employeeDto.setEmpUUID(empUUID);
        EmployeeDTO dto = employeeService.findByEmployee(employeeDto);
        todoDto.setEmployee(dto);
        model.addAttribute("todoDto", todoService.findByTodoOne(todoDto));
        model.addAttribute("employeeDto", dto);
        return "todo/readonly";
    }

    @GetMapping("/modify/{id}")
    public String modify(EmployeeDTO employeeDto, @CookieValue(value = "uuid") String empUUID,
                         @PathVariable("id") Integer id, Model model) {
        employeeDto.setEmpUUID(empUUID);
        model.addAttribute("employeeDto", employeeService.findByEmployee(employeeDto));
        model.addAttribute("todoDto", todoService.findByTodoId(id));
        return "/todo/modify";
    }

    @PostMapping("/modify/update/{id}")
    public String todoUpdateWriting(@PathVariable("id") Integer id,
                                    @ModelAttribute TodoDTO todoDto,
                                    HttpServletRequest request, Model model, EmployeeDTO employeeDto,
                                    @CookieValue(value = "uuid") String empUUID) {
        employeeDto.setEmpUUID(empUUID);
        EmployeeDTO dto = employeeService.findByEmployee(employeeDto);
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
