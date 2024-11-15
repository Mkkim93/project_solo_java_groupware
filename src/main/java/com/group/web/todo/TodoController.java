package com.group.web.todo;

import com.group.application.todo.dto.TodoDTO;
import com.group.application.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/todo")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @GetMapping("/calendar")
    public String showCalendar(Model model, TodoDTO todoDTO) {
        todoDTO.setEmployee(1); // TODO jwt
        model.addAttribute("todoDTO", todoService.TodoSave(todoDTO));
        return "/todo/calendar";
    }
}
