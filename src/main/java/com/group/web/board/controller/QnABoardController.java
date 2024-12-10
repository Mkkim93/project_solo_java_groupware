package com.group.web.board.controller;

import com.group.application.board.dto.QnABoardDTO;
import com.group.application.board.service.BoardService;
import com.group.application.board.service.CommentService;
import com.group.application.board.service.QnABoardService;
import com.group.application.cookie.service.CookieService;
import com.group.application.hr.dto.EmployeeDTO;
import com.group.application.hr.service.EmployeeService;
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
    private final EmployeeService employeeService;
    private final CookieService cookieService;

    @GetMapping("/list")
    public String view(@RequestParam(value = "page", defaultValue = "0") int page,
                       @RequestParam(value = "size", defaultValue = "5") int size,
                       Model model) {
        Pageable pageRequest = PageRequest.of(page, size);
        Page<QnABoardDTO> qnaBoardDto = qnABoardService.findAll(pageRequest);

        model.addAttribute("qnaBoardList", qnaBoardDto);
        return "board/qna/list";
    }

    @GetMapping("/detail")
    public String detail(@RequestParam(value = "id") Integer id,
                         @RequestParam(value = "boardPass", required = false) String boardPass,
                         @RequestParam(value = "page", defaultValue = "0") int page,
                         @RequestParam(value = "size", defaultValue = "10") int size,
                         @CookieValue(value = "jwtToken") String token,
                         Model model) {

        String uuid = cookieService.getEmpUUIDFromCookiesV2(token);
        EmployeeDTO dto = employeeService.findByEmployee(uuid);

        model.addAttribute("employeeDto", dto);
        QnABoardDTO qnaBoardDto = qnABoardService.findById(id, boardPass);

        // 댓글 관련 model & 페이징 객체
        model.addAttribute("qnaBoardDto", qnABoardService.findByOne(id, boardPass));
        model.addAttribute("boardDto", boardService.findByOnlyId(qnaBoardDto.getBoardId()));

        PageRequest pageRequest = PageRequest.of(page, size);
        model.addAttribute("commentDto", commentService.findAll(qnaBoardDto.getBoardId(), pageRequest));

        return "/board/qna/detail";  // 상세 페이지를 렌더링
    }

    @GetMapping("/write")
    public String write(QnABoardDTO qnaBoardDto, Model model) {
        model.addAttribute("qnaBoardDto", qnaBoardDto);
        return "/board/qna/write";
    }

    @PostMapping("/write")
    public String writeProc(@CookieValue(value = "jwtToken") String token,
                            QnABoardDTO qnaBoardDto) {
        String uuid = cookieService.getEmpUUIDFromCookiesV2(token);
        EmployeeDTO dto = employeeService.findByEmployee(uuid);
        qnaBoardDto.setEmployee(dto);
        qnABoardService.save(qnaBoardDto);
        return "redirect:/board/qna/list";
    }

    @GetMapping("/modify/{id}")
    public String modify(@PathVariable("id") Integer id,
                         QnABoardDTO qnABoardDto, Model model) {
        model.addAttribute("qnaBoardDto", qnABoardService.findByModify(qnABoardDto));
        return "/board/qna/modify";
    }

    @PostMapping("/modify/update/{id}")
    public String modifyProc(@CookieValue(value = "jwtToken") String token,
                             @PathVariable("id") Integer id,
                             @ModelAttribute QnABoardDTO qnaBoardDto) {

        String uuid = cookieService.getEmpUUIDFromCookiesV2(token);
        EmployeeDTO dto = employeeService.findByEmployee(uuid);

        QnABoardDTO qnaBoardTemp = qnABoardService.findByOnlyId(id);
        qnaBoardTemp.setBoardTitle(qnaBoardDto.getBoardTitle());
        qnaBoardTemp.setBoardContent(qnaBoardDto.getBoardContent());
        qnaBoardTemp.setEmployee(dto);

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