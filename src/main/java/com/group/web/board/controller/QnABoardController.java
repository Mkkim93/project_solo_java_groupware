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
                                  @PathVariable("id") Integer id,
                                  @RequestParam("qBoardPass") String qBoardPass) {
        model.addAttribute("qnABoardDTO", qnABoardService.findByIdQnABoard(id, qBoardPass));
        return "/board/qnaboarddetailview";
    }

    @GetMapping("/qnaboardmodify/{id}")
    public String boardModifyView(Model model,
                                  @PathVariable("id") Integer id,
                                  @RequestParam("qBoardPass") String qBoardPass) {
        model.addAttribute("qnABoardDTO", qnABoardService.findQnABoardByQnAAndBoardId(id, qBoardPass));
        return "/board/qnaboardmodify";
    }

    @PostMapping("/qnaboardmodify/update/{id}")
    public String boardModifyWriting(@PathVariable("id") Integer id,
                                     @RequestParam(value = "qBoardPass", required = false, defaultValue = "default") String qBoardPass,
                                     @ModelAttribute QnABoardDTO qnABoardDTO) {
        QnABoardDTO qnABoardTemp = qnABoardService.findQnABoardByQnAAndBoardId(id, qBoardPass);
        qnABoardTemp.setBoardTitle(qnABoardDTO.getBoardTitle());
        qnABoardTemp.setBoardContent(qnABoardDTO.getBoardContent());
        qnABoardTemp.setQBoardPass(qnABoardDTO.getQBoardPass());
        qnABoardService.updateQnABoard(qnABoardTemp);
        return "redirect:/board/qnaboardlist";
    }
}
