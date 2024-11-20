package com.group.web.todo;

import com.group.application.todo.dto.TodoDTO;
import com.group.application.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/todo")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @GetMapping("/calendar")
    public String showCalendar(Model model, @ModelAttribute TodoDTO todoDTO) {
        todoDTO.setEmployee(1); // TODO jwt
        model.addAttribute("todoDTO", todoService.findByTodoOne(todoDTO));
        return "todo/calendar";
    }

    @GetMapping("/readonly")
    public String readOnlyCalender(Model model, TodoDTO todoDTO) {
        todoDTO.setEmployee(1); // TODO jwt
        model.addAttribute("todoDTO", todoService.findByTodoOne(todoDTO));
        return "todo/readonly";
    }

    // TODO 수정 페이지 구현 (비동기로 할지 별도 페이지 이동 할지 고민중..)

}
