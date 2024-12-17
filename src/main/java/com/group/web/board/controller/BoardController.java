package com.group.web.board.controller;

import com.group.application.board.dto.BoardDTO;
import com.group.application.board.service.BoardService;
import com.group.application.board.service.CommentService;
import com.group.application.cookie.service.CookieService;
import com.group.application.hr.dto.EmployeeDTO;
import com.group.application.hr.service.EmployeeService;
import com.group.domain.hr.entity.Employee;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.hibernate.mapping.TableOwner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/board/all")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    private final CommentService commentService;
    private final CookieService cookieService;
    private final EmployeeService employeeService;

    @GetMapping("/list")
    public String view(@RequestParam(value = "page", defaultValue = "0") int page,
                       @RequestParam(value = "size", defaultValue = "15") int size,
                       Model model) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<BoardDTO> boardDto = boardService.findAll(pageRequest);
        model.addAttribute("allBoardList", boardDto);
        return "/board/all/list";
    }

    @GetMapping("/detail")
    public String detail(@RequestParam(value = "page", defaultValue = "0") int page,
                         @RequestParam(value = "size", defaultValue = "10") int size,
                         @RequestParam("id") Integer id,
                         @CookieValue("jwtToken") String token,
                         Model model) {
        String uuid = cookieService.getEmpUUIDFromCookiesV2(token);
        EmployeeDTO dto = employeeService.findByEmployee(uuid);
        model.addAttribute("employeeDto", dto);
        model.addAttribute("boardDto", boardService.findByOne(id));

        PageRequest pageRequest = PageRequest.of(page, size);
        model.addAttribute("commentDto", commentService.findAll(id, pageRequest));
        return "/board/all/detail";
    }

    @GetMapping("/write")
    public String write(Model model) {
        model.addAttribute("boardDto", new BoardDTO());
        return "/board/all/write";
    }

    @PostMapping("/write")
    public String writeProc(@CookieValue(value = "jwtToken") String token, @ModelAttribute BoardDTO boardDto) {
        String uuid = cookieService.getEmpUUIDFromCookiesV2(token);
        EmployeeDTO dto = employeeService.findByEmployee(uuid);
        boardDto.setterEmpDto(dto);
        boardService.saveAll(boardDto);
        return "redirect:/board/all/list";
    }

    @GetMapping("/modify/{id}")
    public String modify(@PathVariable("id") Integer id, Model model) {
        BoardDTO boardDto = boardService.getBoardById(id);
        model.addAttribute("boardDto", boardDto);
        return "/board/all/modify";
    }

    @PostMapping("/modify/update/{id}")
    public String modifyProc(@PathVariable("id") Integer id,
                             @ModelAttribute BoardDTO boardDto,
                             @CookieValue(value = "jwtToken") String token) {
        // TODO 하나의 기능에 너무 많은 서비스 레이어가 포함 (수정 필요 할듯)
        // 기존 게시글을 조회하여 수정
        BoardDTO boardTemp = boardService.getBoardById(id);
        String uuid = cookieService.getEmpUUIDFromCookiesV2(token);
        EmployeeDTO dto = employeeService.findByEmployeeEntity(uuid);
        boardTemp.updateBoard(boardDto, dto);
        boardService.update(boardTemp);
        return "redirect:/board/all/list";
    }

    @GetMapping("/detail/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        BoardDTO boardDto = boardService.findByOne(id);
        boardService.delete(boardDto.getId());
        return "redirect:/board/all/list";
    }
}