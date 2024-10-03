package com.group.web.board.controller;

import com.group.application.board.dto.QnABoardDTO;
import com.group.application.board.service.QnABoardService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/board")
public class QnABoardController {

    private QnABoardService qnABoardService;

    public QnABoardController(QnABoardService qnABoardService) {
        this.qnABoardService = qnABoardService;
    }

    @GetMapping("/qnaboardlist")
    public String boardView(Model model,
                            @RequestParam(value = "page", defaultValue = "0") int page,
                            @RequestParam(value = "size", defaultValue = "5") int size) {
        Pageable pageRequest = PageRequest.of(page, size);
        Page<QnABoardDTO> qnABoardDTO = qnABoardService.findAllByQnABoard(pageRequest);
        model.addAttribute("qnABoardDTO", qnABoardDTO);
        return "/board/qnaboardlist";
    }

    @GetMapping("/qnaboardwrite")
    public String boardWriteForm(Model model, QnABoardDTO qnABoardDTO) {
        model.addAttribute("qnABoardDTO", qnABoardDTO);
        return "/board/qnaboardwrite";
    }

    @PostMapping("/qnaboardwrite")
    public String boardWriting(QnABoardDTO qnABoardDTO) {
        qnABoardService.saveQnABoard(qnABoardDTO);
        return "redirect:/board/qnaboardlist";
    }

    @GetMapping(value = "/qnaboarddetailview/{id}")
    public String boardDetailView(Model model,
                                  @PathVariable("id") int id,
                                  @RequestParam("qBoardPass") int qBoardPass) {
        model.addAttribute("qnABoardDTO", qnABoardService.findByIdQnABoard(id, qBoardPass));
        return "/board/qnaboarddetailview";
    }
}
