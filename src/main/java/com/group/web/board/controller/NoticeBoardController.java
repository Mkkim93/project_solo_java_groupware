package com.group.web.board.controller;

import com.group.application.board.dto.NoticeBoardDTO;
import com.group.application.board.service.BoardService;
import com.group.application.board.service.CommentService;
import com.group.application.board.service.NoticeBoardService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/board")
public class NoticeBoardController {

    private final NoticeBoardService noticeBoardService;
    private final CommentService commentService;
    private final BoardService boardService;

    public NoticeBoardController(NoticeBoardService noticeBoardService, CommentService commentService,
                                 BoardService boardService) {
        this.noticeBoardService = noticeBoardService;
        this.commentService = commentService;
        this.boardService = boardService;
    }

    @GetMapping("/noticeboardlist")
    public String boardListView(Model model,
                            @RequestParam(value = "page", defaultValue = "0") int page,
                            @RequestParam(value = "size", defaultValue = "5") int size) {
        Pageable pageRequest = PageRequest.of(page, size);
        Page<NoticeBoardDTO> noticeBoardDTO = noticeBoardService.findAllByNoticeBoard(pageRequest);
        model.addAttribute("noticeBoardDTO", noticeBoardDTO);
        return "/board/noticeboardlist";
    }

    @GetMapping("/noticeboardwrite")
    public String boardWriteForm(Model model, NoticeBoardDTO noticeBoardDTO) {
        model.addAttribute("noticeBoardDTO", noticeBoardDTO);
        return "/board/noticeboardwrite";
    }

    @PostMapping("/noticeboardwrite")
    public String boardWriting(NoticeBoardDTO noticeBoardDTO) {
        noticeBoardService.saveNoticeBoard(noticeBoardDTO);
        return "redirect:/board/noticeboardlist";
    }

    @GetMapping("/noticeboarddetailview")
    public String boardDetailView(Model model,
                                  @RequestParam("id") Integer id,
                                  @RequestParam(value = "page", defaultValue = "0") int page,
                                  @RequestParam(value = "size", defaultValue = "10") int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        NoticeBoardDTO noticeBoardDTO = noticeBoardService.findById(id);
        model.addAttribute("commentDTO", commentService.findAll(noticeBoardDTO.getBoardId(), pageRequest));
        model.addAttribute("noticeBoardDTO", noticeBoardService.findByIdNoticeBoard(id));
        model.addAttribute("boardDTO", boardService.findByIdOnly(noticeBoardDTO.getBoardId()));
        return "/board/noticeboarddetailview";
    }

    @GetMapping("/noticeboardmodify/{id}")
    public String boardModifyView(Model model,
                                  @PathVariable("id") Integer id) {
        model.addAttribute("noticeBoardDTO", noticeBoardService.findById(id));
        return "/board/noticeboardmodify";
    }

    @PostMapping("/noticeboardmodify/update/{id}")
    public String boardWritingForm(@PathVariable("id") Integer id,
                                   @ModelAttribute NoticeBoardDTO noticeBoardDTO) {
        NoticeBoardDTO noticeBoardTemp = noticeBoardService.findById(id);
        noticeBoardTemp.setBoardTitle(noticeBoardDTO.getBoardTitle());
        noticeBoardTemp.setBoardContent(noticeBoardTemp.getBoardContent());
        noticeBoardService.updateNoticeBoard(noticeBoardTemp);
        return "redirect:/board/noticeboardlist";
    }

    @GetMapping("/noticeboarddetailview/delete/{id}")
    public String deleteBoard(@PathVariable("id") Integer id) {
        NoticeBoardDTO noticeBoardDTO = noticeBoardService.findById(id);
        noticeBoardService.deleteBoard(noticeBoardDTO.getBoardId());
        return "redirect:/board/noticeboardlist";
    }
}
