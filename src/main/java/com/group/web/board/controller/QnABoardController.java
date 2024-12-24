package com.group.web.board.controller;

import com.group.application.board.dto.QnABoardDTO;
import com.group.application.board.service.BoardService;
import com.group.application.board.service.CommentService;
import com.group.application.board.service.QnABoardService;
import com.group.application.hr.dto.EmployeeDTO;
import com.group.application.hr.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/board/qna")
@RequiredArgsConstructor
public class QnABoardController {

    private final QnABoardService qnABoardService;
    private final CommentService commentService;
    private final BoardService boardService;
    private final EmployeeService employeeService;

    @GetMapping("/list")
    public String view(@RequestParam(value = "page", defaultValue = "0") int page,
                       @RequestParam(value = "size", defaultValue = "15") int size,
                       Model model, String searchKeyword) {
        Pageable pageRequest = PageRequest.of(page, size);
        Page<QnABoardDTO> qnaBoardDto = qnABoardService.findAll(searchKeyword, pageRequest);
        model.addAttribute("qnaBoardList", qnaBoardDto);
        return "board/qna/list";
    }

    @GetMapping("/detail")
    public String detail(@RequestParam(value = "id") Integer id,
                         @RequestParam(value = "boardPass", required = false) String boardPass,
                         @RequestParam(value = "page", defaultValue = "0") int page,
                         @RequestParam(value = "size", defaultValue = "10") int size,
                         @CookieValue(value = "uuid") String empUUID, EmployeeDTO employeeDto,
                         Model model) {
        employeeDto.setEmpUUID(empUUID);
        model.addAttribute("employeeDto", employeeService.findByEmployee(employeeDto));
        QnABoardDTO qnaBoardDto = qnABoardService.findById(id, boardPass);

        model.addAttribute("qnaBoardDto", qnABoardService.findByOne(id, boardPass));
        model.addAttribute("boardDto", boardService.findByOnlyId(qnaBoardDto.getBoardId()));

        // 댓글 관련 model & 페이징 객체
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
    public String writeProc(@CookieValue(value = "uuid") String empUUID,
                            QnABoardDTO qnaBoardDto, EmployeeDTO employeeDto) {
        employeeDto.setEmpUUID(empUUID);
        qnaBoardDto.setEmployee(employeeService.findByEmployee(employeeDto));
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
    public String modifyProc(@CookieValue(value = "uuid") String empUUID,
                             @PathVariable("id") Integer id, EmployeeDTO employeeDto,
                             @ModelAttribute QnABoardDTO qnaBoardDto) {
        employeeDto.setEmpUUID(empUUID);
        QnABoardDTO qnaBoardTemp = qnABoardService.findByOnlyId(id);
        qnaBoardTemp.updateBoard(qnaBoardDto, employeeService.findByEmployee(employeeDto));
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