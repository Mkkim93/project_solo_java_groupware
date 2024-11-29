package com.group.web.board.controller;

import com.group.application.board.dto.FreeBoardDTO;
import com.group.application.board.service.BoardService;
import com.group.application.board.service.CommentService;
import com.group.application.board.service.FreeBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/board/free")
@RequiredArgsConstructor
public class FreeBoardController {

    private final FreeBoardService freeBoardService;
    private final CommentService commentService;
    private final BoardService boardService;

    @GetMapping("/list")
    public String view(@RequestParam(value = "page", defaultValue = "0") int page,
                       @RequestParam(value = "size", defaultValue = "5") int size,
                       Model model) {
        Pageable pageRequest = PageRequest.of(page, size);
        Page<FreeBoardDTO> freeBoardDto = freeBoardService.findAll(pageRequest);
        model.addAttribute("freeBoardList", freeBoardDto);
        return "/board/free/list";
    }

    @GetMapping("/detail")
    public String detail(@RequestParam("id") Integer id,
                         @RequestParam(value = "page", defaultValue = "0") int page,
                         @RequestParam(value = "size", defaultValue = "10") int size,
                         Model model) {
        PageRequest pageRequest = PageRequest.of(page, size);
        FreeBoardDTO freeBoardDto = freeBoardService.findById(id);
        model.addAttribute("freeBoardDto", freeBoardService.findByOne(id));
        model.addAttribute("boardDto", boardService.findByOnlyId(freeBoardDto.getBoardId()));
        model.addAttribute("commentDto", commentService.findAll(freeBoardDto.getBoardId(), pageRequest));
        return "/board/free/detail";
    }

    @GetMapping("/write")
    public String write(FreeBoardDTO freeBoardDto, Model model) {
        model.addAttribute("freeBoardDto", freeBoardDto);
        return "board/free/write";
    }

    @PostMapping("/write")
    public String writeProc(FreeBoardDTO freeBoardDto) {
        freeBoardDto.setEmployee(1); // TODO 임시 ID
        freeBoardService.save(freeBoardDto);
        return "redirect:/board/free/list";
    }

    @GetMapping("/modify/{id}")
    public String modify(@PathVariable("id") Integer id , Model model) {
        model.addAttribute("freeBoardDto", freeBoardService.findById(id));
        return "/board/free/modify";
    }

    @PostMapping("/modify/update/{id}")
    public String modifyProc(@PathVariable("id") Integer id,
                             @ModelAttribute FreeBoardDTO freeBoardDto) {
        FreeBoardDTO freeBoardTemp = freeBoardService.findById(id);
        freeBoardTemp.setBoardTitle(freeBoardDto.getBoardTitle());
        freeBoardTemp.setBoardContent(freeBoardDto.getBoardContent());
        freeBoardTemp.setEmployee(1); // TODO 임시 ID
        freeBoardService.update(freeBoardTemp);
        return "redirect:/board/free/list";
    }

    @GetMapping("/detail/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        FreeBoardDTO freeBoardDto = freeBoardService.findById(id);
        freeBoardService.delete(freeBoardDto.getBoardId());
        return "redirect:/board/free/list";
    }
}

