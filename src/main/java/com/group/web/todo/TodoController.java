package com.group.web.todo;

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

    @GetMapping("/detail")
    public String showCalendar(Model model, @ModelAttribute TodoDTO todoDTO) {
        todoDTO.setEmployee(1); // TODO jwt
        model.addAttribute("todoDTO", todoService.findByTodoOne(todoDTO));
        return "/todo/detail";
    }

    @GetMapping("/readonly")
    public String readOnlyCalender(Model model, TodoDTO todoDTO) {
        todoDTO.setEmployee(1); // TODO jwt
        model.addAttribute("todoDTO", todoService.findByTodoOne(todoDTO));
        return "todo/readonly";
    }


    @GetMapping("/modify/{id}")
    public String modify(@PathVariable("id") Integer id,
                             Model model) {
        TodoDTO todoDTO = todoService.findByTodoId(id);
        model.addAttribute("todoDTO", todoDTO);
        return "/todo/modify";
    }

    @PostMapping("/modify/update/{id}")
    public String todoUpdateWriting(@PathVariable("id") Integer id,
                                    @ModelAttribute TodoDTO todoDTO) {
        TodoDTO todoTemp = todoService.findByTodoId(id);
        todoTemp.setTodoTitle(todoDTO.getTodoTitle());
        todoTemp.setTodoContent(todoDTO.getTodoContent());
        todoTemp.setTodoStartDate(todoDTO.getTodoStartDate());
        todoTemp.setTodoEndDate(todoDTO.getTodoEndDate());
        todoTemp.setTodoUpdate(LocalDateTime.now());
        todoTemp.setEmployee(1);
        todoService.updateTodo(todoTemp);
        return "redirect:/todo/detail";
    }
}
