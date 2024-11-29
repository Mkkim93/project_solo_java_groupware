package com.group.web.board.controller;

import com.group.application.board.dto.QnABoardDTO;
import com.group.application.board.service.BoardService;
import com.group.application.board.service.CommentService;
import com.group.application.board.service.QnABoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/board/qna")
@RequiredArgsConstructor
public class QnABoardController {

    private final QnABoardService qnABoardService;
    private final CommentService commentService;
    private final BoardService boardService;

    @GetMapping("/list")
    public String view(@RequestParam(value = "page", defaultValue = "0") int page,
                       @RequestParam(value = "size", defaultValue = "5") int size,
                       Model model) {
        Pageable pageRequest = PageRequest.of(page, size);
        Page<QnABoardDTO> qnaBoardDto = qnABoardService.findAll(pageRequest);
        model.addAttribute("qnaBoardList", qnaBoardDto);
        return "/board/qna/list";
    }

    @GetMapping("/detail")
    public String detail(@RequestParam(value = "id") Integer id,
                         @RequestParam(value = "boardPass", required = false) String boardPass,
                         @RequestParam(value = "page", defaultValue = "0") int page,
                         @RequestParam(value = "size", defaultValue = "10") int size,
                         RedirectAttributes redirectAttributes, Model model) {
        if (boardPass == null) {
            model.addAttribute("notBoardDto", qnABoardService.findByIdNotPass(id));
            return "/board/qna/detail";
        }

        QnABoardDTO qnaBoardDto = qnABoardService.findById(id, boardPass);
        if (!qnaBoardDto.getBoardPass().equals(boardPass)) {
            redirectAttributes.addFlashAttribute("failPassWord", "failPassWord");
            return "redirect:/board/qna/list";  // 게시판 목록으로 리다이렉트
        }

        // 댓글 관련 model & 페이징 객체
        PageRequest pageRequest = PageRequest.of(page, size);
        model.addAttribute("qnaBoardDto", qnABoardService.findByOne(id, boardPass));
        model.addAttribute("boardDto", boardService.findByOnlyId(qnaBoardDto.getBoardId()));
        model.addAttribute("commentDto", commentService.findAll(qnaBoardDto.getBoardId(), pageRequest));
        // 비밀번호가 맞는 경우 상세 페이지를 모델에 추가하여 반환

        // 비밀번호가 틀린 경우 TODO : NPE 로직
        return "/board/qna/detail";  // 상세 페이지를 렌더링
    }

    @GetMapping("/write")
    public String write(QnABoardDTO qnaBoardDto, Model model) {
        model.addAttribute("qnaBoardDto", qnaBoardDto);
        return "/board/qna/write";
    }

    @PostMapping("/write")
    public String writeProc(QnABoardDTO qnaBoardDto) {
        qnaBoardDto.setEmployee(1); // TODO 임시 ID
        qnABoardService.save(qnaBoardDto);
        return "redirect:/board/qna/list";
    }

    @GetMapping("modify/{id}")
    public String modify(@PathVariable("id") Integer id,
                         @RequestParam(value = "boardPass", required = false) String boardPass,
                         Model model) {
        model.addAttribute("qnaBoardDto", qnABoardService.findById(id, boardPass));
        return "/board/qna/modify";
    }

    @PostMapping("/modify/update/{id}")
    public String modifyProc(@PathVariable("id") Integer id,
                             @RequestParam(value = "boardPass", required = false) String boardPass,
                             @ModelAttribute QnABoardDTO qnaBoardDto) {
        QnABoardDTO qnaBoardTemp = qnABoardService.findById(id, boardPass);
        qnaBoardTemp.setBoardTitle(qnaBoardDto.getBoardTitle());
        qnaBoardTemp.setBoardContent(qnaBoardDto.getBoardContent());
        qnaBoardTemp.setEmployee(1); // TODO 임시 ID
        qnABoardService.update(qnaBoardTemp);
        return "redirect:/board/qna/list";
    }

    @GetMapping("/detail/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        QnABoardDTO qnaboarddto = qnABoardService.findByOnlyId(id);
        qnABoardService.delete(qnaboarddto.getBoardId());
        return "redirect:/board/qna/list";
    }
}