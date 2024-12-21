package com.group.web.board.controller;

import com.group.application.board.dto.NoticeBoardDTO;
import com.group.application.board.service.BoardService;
import com.group.application.board.service.CommentService;
import com.group.application.board.service.NoticeBoardService;
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
@RequestMapping("/board/notice")
@RequiredArgsConstructor
public class NoticeBoardController {

    private final NoticeBoardService noticeBoardService;
    private final CommentService commentService;
    private final BoardService boardService;
    private final EmployeeService employeeService;

    @GetMapping("/list")
    public String view(@RequestParam(value = "page", defaultValue = "0") int page,
                       @RequestParam(value = "size", defaultValue = "15") int size,
                       Model model) {
        Pageable pageRequest = PageRequest.of(page, size);
        Page<NoticeBoardDTO> noticeBoardDto = noticeBoardService.findAll(pageRequest);
        model.addAttribute("noticeBoardList", noticeBoardDto);
        return "/board/notice/list";
    }

    @GetMapping("/detail")
    public String detail(@RequestParam("id") Integer id,
                         @RequestParam(value = "page", defaultValue = "0") int page,
                         @RequestParam(value = "size", defaultValue = "10") int size,
                         @CookieValue("uuid") String empUUID, EmployeeDTO employeeDto,
                         Model model) {
        employeeDto.setEmpUUID(empUUID);
        EmployeeDTO dto = employeeService.findByEmployee(employeeDto);
        model.addAttribute("employeeDto", dto);
        PageRequest pageRequest = PageRequest.of(page, size);
        NoticeBoardDTO noticeboarddto = noticeBoardService.findByOnlyId(id);
        model.addAttribute("commentDto", commentService.findAll(noticeboarddto.getBoardId(), pageRequest));
        model.addAttribute("noticeBoardDto", noticeBoardService.findByOne(id));
        model.addAttribute("boardDto", boardService.findByOnlyId(noticeboarddto.getBoardId()));
        return "/board/notice/detail";
    }

    @GetMapping("/write")
    public String write(NoticeBoardDTO noticeBoardDto, Model model) {
        model.addAttribute("noticeBoardDto", noticeBoardDto);
        return "/board/notice/write";
    }

    @PostMapping("/write")
    public String writeProc(NoticeBoardDTO noticeBoardDto, EmployeeDTO employeeDto,
                            @CookieValue(value = "uuid") String empUUID) {
        employeeDto.setEmpUUID(empUUID);
        EmployeeDTO dto = employeeService.findByEmployee(employeeDto);
        noticeBoardDto.setEmployee(dto);
        noticeBoardService.save(noticeBoardDto);
        return "redirect:/board/notice/list";
    }

    @GetMapping("/modify/{id}")
    public String modify(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("noticeBoardDto", noticeBoardService.findByOnlyId(id));
        return "/board/notice/modify";
    }

    @PostMapping("/modify/update/{id}")
    public String modifyProc(@PathVariable("id") Integer id,
                             @ModelAttribute NoticeBoardDTO noticeBoardDto,
                             @CookieValue(value = "uuid") String empUUID,
                             EmployeeDTO employeeDto) {
        employeeDto.setEmpUUID(empUUID);
        EmployeeDTO dto = employeeService.findByEmployee(employeeDto);
        NoticeBoardDTO noticeBoardTemp = noticeBoardService.findByOnlyId(id);
        noticeBoardTemp.updateBoard(noticeBoardDto, dto);
        noticeBoardService.update(noticeBoardTemp);
        return "redirect:/board/notice/list";
    }

    @GetMapping("/detail/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        NoticeBoardDTO noticeBoardDto = noticeBoardService.findByOnlyId(id);
        noticeBoardService.delete(noticeBoardDto.getBoardId());
        return "redirect:/board/notice/list";
    }
}
