package com.group.web.board.controller;

import com.group.application.board.dto.BoardDTO;
import com.group.application.board.service.BoardService;
import com.group.application.login.dto.CustomUserDetails;
import com.group.domain.board.entity.Board;
import com.group.domain.hr.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/board")
public class BoardController {

    private BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/boardlist")
    public String boardView(Model model,
                            @RequestParam(value = "page", defaultValue = "0") int page,
                            @RequestParam(value = "size", defaultValue = "5") int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<BoardDTO> boardDTO = boardService.findAllByBoard(pageRequest);
        model.addAttribute("boardDTO", boardDTO);
        return "/board/boardlist";
    }

    @GetMapping("/boardwrite")
    public String boardWrite(Model model) {
        model.addAttribute("boardDTO", new BoardDTO());
        return "/board/boardwrite";
    }

    @PostMapping("/boardwrite")
    public String boardWritePro(BoardDTO boardDTO) {
            boardService.saveProcessAllBoard(boardDTO);
            return "redirect:/board/boardlist";
    }

    @GetMapping("/boarddetailview")
    public String detailView(Model model,
                             @RequestParam("id") Integer id) {
        model.addAttribute("boardDTO", boardService.findById(id));
        return "/board/boarddetailview";
    }

    @GetMapping("/boardmodify/{id}")
    public String boardModifyView(Model model, @PathVariable("id") Integer id) {
        BoardDTO boardDTO = boardService.getBoardById(id);
        model.addAttribute("boardDTO", boardDTO);
        return "/board/boardmodify";
    }

    @PostMapping("/boardmodify/update/{id}")
    public String boardModifyWriting(@PathVariable("id") Integer id,
                                     @ModelAttribute BoardDTO boardDTO) {
        // 기존 게시글을 조회하여 수정
        BoardDTO boardTemp = boardService.getBoardById(id);
        // 새로운 값으로 수정
        boardTemp.setId(id);
        boardTemp.setBoardTitle(boardDTO.getBoardTitle());
        boardTemp.setBoardContent(boardDTO.getBoardContent());
        boardService.updateBoard(boardTemp);
        return "redirect:/board/boardlist";
    }
}


