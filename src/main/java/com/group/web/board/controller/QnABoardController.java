package com.group.web.board.controller;

import com.group.application.board.dto.BoardDTO;
import com.group.application.board.dto.QnABoardDTO;
import com.group.application.board.service.QnABoardService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @GetMapping("/qnaboarddetailview")
    public String boardDetailView(@RequestParam("id") Integer id,
                                  @RequestParam(value = "qBoardPass", required = false) String qBoardPass,
                                  RedirectAttributes redirectAttributes,
                                  Model model) {
        QnABoardDTO qnABoardDTO = qnABoardService.findByIdOnly(id, qBoardPass);

        // 비밀번호가 틀린 경우 TODO : NPE 로직
        if (qnABoardDTO == null && (qnABoardDTO.getQBoardPass() != null && !qnABoardDTO.getQBoardPass().equals(qBoardPass))
                ) {
            redirectAttributes.addFlashAttribute("qBoardPass", "비밀번호가 틀렸습니다.");
            return "redirect:/board/qnaboardlist";  // 게시판 목록으로 리다이렉트
        }

        // 비밀번호가 맞는 경우 상세 페이지를 모델에 추가하여 반환
        model.addAttribute("qnABoardDTO", qnABoardDTO);
        return "/board/qnaboarddetailview";  // 상세 페이지를 렌더링
    }


    @GetMapping("qnaboardmodify/{id}")
    public String boardModifyView(Model model,
                                  @PathVariable("id") Integer id,
                                  @RequestParam(value = "qBoardPass", required = false) String qBoardPass) {
        model.addAttribute("qnABoardDTO", qnABoardService.findById(id, qBoardPass));
        return "/board/qnaboardmodify";
    }

    @PostMapping("/qnaboardmodify/update/{id}")
    public String boardModifyWriting(@PathVariable("id") Integer id,
                                     @RequestParam(value = "qBoardPass", required = false) String qBoardPass,
                                     @ModelAttribute QnABoardDTO qnABoardDTO) {
        QnABoardDTO qnABoardTemp = qnABoardService.findById(id, qBoardPass);
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