package com.group.web.board.controller;

import com.group.application.board.dto.BoardDTO;
import com.group.application.board.service.BoardService;
import com.group.application.board.service.CommentService;
import lombok.RequiredArgsConstructor;
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

    @GetMapping("/list")
    public String view(@RequestParam(value = "page", defaultValue = "0") int page,
                       @RequestParam(value = "size", defaultValue = "5") int size,
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
                         Model model) {
        PageRequest pageRequest = PageRequest.of(page, size);
        model.addAttribute("boardDto", boardService.findByOne(id));
        model.addAttribute("commentDto", commentService.findAll(id, pageRequest));
        return "/board/all/detail";
    }

    @GetMapping("/write")
    public String write(Model model) {
        model.addAttribute("boardDto", new BoardDTO());
        return "/board/all/write";
    }

    @PostMapping("/write")
    public String writeProc(BoardDTO boardDto) {
        boardDto.setEmployee(1); // TODO 임시 ID
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
                             @ModelAttribute BoardDTO boardDto) {
        // 기존 게시글을 조회하여 수정
        BoardDTO boardTemp = boardService.getBoardById(id);

        // 새로운 값으로 수정
        boardTemp.setId(id);
        boardTemp.setBoardTitle(boardDto.getBoardTitle());
        boardTemp.setBoardContent(boardDto.getBoardContent());
        boardTemp.setEmployee(1); // TODO 임시 ID
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