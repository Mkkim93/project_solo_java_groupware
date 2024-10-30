package com.group.web.board.controller;

import com.group.application.board.dto.FreeBoardDTO;
import com.group.application.board.service.BoardService;
import com.group.application.board.service.CommentService;
import com.group.application.board.service.FreeBoardService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/board")
public class FreeBoardController {

    private final FreeBoardService freeBoardService;
    private final CommentService commentService;
    private final BoardService boardService;

    public FreeBoardController(FreeBoardService freeBoardService, CommentService commentService,
                               BoardService boardService) {
        this.freeBoardService = freeBoardService;
        this.commentService = commentService;
        this.boardService = boardService;
    }

    @GetMapping("/freeboardlist")
    public String boardView(Model model,
                            @RequestParam(value = "page", defaultValue = "0") int page,
                            @RequestParam(value = "size", defaultValue = "5") int size) {
        Pageable pageRequest = PageRequest.of(page, size);
        Page<FreeBoardDTO> freeBoardDTO = freeBoardService.findAllByFreeBoard(pageRequest);
        model.addAttribute("freeBoardDTO", freeBoardDTO);
        return "/board/freeboardlist";
    }

    @GetMapping("/freeboardwrite")
    public String boardWriteForm(Model model, FreeBoardDTO freeBoardDTO) {
        model.addAttribute("freeBoardDTO", freeBoardDTO);
        return "board/freeboardwrite";
    }

    @PostMapping("/freeboardwrite")
    public String boardWriting(FreeBoardDTO freeBoardDTO) {
        freeBoardService.saveFreeBoard(freeBoardDTO);
        return "redirect:/board/freeboardlist";
    }

    @GetMapping("/freeboarddetailview")
    public String boardDetailView(Model model,
                                  @RequestParam("id") Integer id,
                                  @RequestParam(value = "page", defaultValue = "0") int page,
                                  @RequestParam(value = "size", defaultValue = "10") int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        FreeBoardDTO freeBoardDTO = freeBoardService.findById(id);
        model.addAttribute("freeBoardDTO", freeBoardService.findByIdOnlyFreeBoard(id));
        model.addAttribute("boardDTO", boardService.findByIdOnly(freeBoardDTO.getBoardId()));
        model.addAttribute("commentDTO", commentService.findAll(freeBoardDTO.getBoardId(), pageRequest));
        return "/board/freeboarddetailview";
    }

    @GetMapping("/freeboardmodify/{id}")
    public String boardModifyView(Model model, @PathVariable("id") Integer id) {
        model.addAttribute("freeBoardDTO", freeBoardService.findById(id));
        return "/board/freeboardmodify";
    }

    @PostMapping("/freeboardmodify/update/{id}")
    public String freeBoardModifyWriting(@PathVariable("id") Integer id,
                                         @ModelAttribute FreeBoardDTO freeBoardDTO) {
        FreeBoardDTO freeBoardTemp = freeBoardService.findById(id);
        freeBoardTemp.setBoardTitle(freeBoardDTO.getBoardTitle());
        freeBoardTemp.setBoardContent(freeBoardDTO.getBoardContent());
        freeBoardService.updateFreeBoard(freeBoardTemp);
        return "redirect:/board/freeboardlist";
    }

    @GetMapping("/freeboarddetailview/delete/{id}")
    public String deleteBoard(@PathVariable("id") Integer id) {
        FreeBoardDTO freeBoardDTO = freeBoardService.findById(id);
        freeBoardService.deleteBoard(freeBoardDTO.getBoardId());
        return "redirect:/board/freeboardlist";
    }
}

