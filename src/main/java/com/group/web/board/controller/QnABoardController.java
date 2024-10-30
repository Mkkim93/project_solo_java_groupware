package com.group.web.board.controller;

import com.group.application.board.dto.QnABoardDTO;
import com.group.application.board.service.BoardService;
import com.group.application.board.service.CommentService;
import com.group.application.board.service.QnABoardService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.IllegalFormatCodePointException;

@Controller
@RequestMapping("/board")
public class QnABoardController {

    private final QnABoardService qnABoardService;
    private final CommentService commentService;
    private final BoardService boardService;

    public QnABoardController(QnABoardService qnABoardService,
                              CommentService commentService,
                              BoardService boardService) {
        this.qnABoardService = qnABoardService;
        this.commentService = commentService;
        this.boardService = boardService;
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

    @GetMapping("/qnaboarddetailview")
    public String boardDetailView(@RequestParam("id") Integer id,
                                  @RequestParam(value = "boardPass", required = false) String boardPass,
                                  @RequestParam(value = "page", defaultValue = "0") int page,
                                  @RequestParam(value = "size", defaultValue = "10") int size,
                                  RedirectAttributes redirectAttributes,
                                  Model model) {
        QnABoardDTO qnABoardDTO = qnABoardService.findById(id, boardPass);
        if (!qnABoardDTO.getBoardPass().equals(boardPass)) {
            redirectAttributes.addFlashAttribute("failPassWord", "failPassWord");
            return "redirect:/board/qnaboardlist";  // 게시판 목록으로 리다이렉트
        }
        // 댓글 관련 model & 페이징 객체
        PageRequest pageRequest = PageRequest.of(page, size);
        model.addAttribute("qnABoardDTO", qnABoardService.findByIdOnly(id, boardPass));
        model.addAttribute("boardDTO", boardService.findByIdOnly(qnABoardDTO.getBoardId()));
        model.addAttribute("commentDTO", commentService.findAll(qnABoardDTO.getBoardId(), pageRequest));
        // 비밀번호가 맞는 경우 상세 페이지를 모델에 추가하여 반환

        // 비밀번호가 틀린 경우 TODO : NPE 로직
        return "/board/qnaboarddetailview";  // 상세 페이지를 렌더링

    }

    @GetMapping("qnaboardmodify/{id}")
    public String boardModifyView(Model model,
                                  @PathVariable("id") Integer id,
                                  @RequestParam(value = "boardPass", required = false) String boardPass) {
        model.addAttribute("qnABoardDTO", qnABoardService.findById(id, boardPass));
        return "/board/qnaboardmodify";
    }

    @PostMapping("/qnaboardmodify/update/{id}")
    public String boardModifyWriting(@PathVariable("id") Integer id,
                                     @RequestParam(value = "boardPass", required = false) String boardPass,
                                     @ModelAttribute QnABoardDTO qnABoardDTO) {
        QnABoardDTO qnABoardTemp = qnABoardService.findById(id, boardPass);
        qnABoardTemp.setBoardTitle(qnABoardDTO.getBoardTitle());
        qnABoardTemp.setBoardContent(qnABoardDTO.getBoardContent());
        qnABoardService.updateQnABoard(qnABoardTemp);
        return "redirect:/board/qnaboardlist";
    }

    @GetMapping("/qnaboarddetailview/delete/{id}")
    public String deleteBoard(@PathVariable("id") Integer id) {
        QnABoardDTO qnaBoardDTO = qnABoardService.findByIdOne(id);
        qnABoardService.deleteBoard(qnaBoardDTO.getBoardId());
        return "redirect:/board/qnaboardlist";
    }
}