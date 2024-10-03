package com.group.web.board.controller;

import com.group.application.board.dto.BoardDTO;
import com.group.application.board.dto.FileBoardDTO;
import com.group.application.board.dto.FreeBoardDTO;
import com.group.application.board.service.FileBoardService;
import com.group.application.board.service.FreeBoardService;
import com.group.application.jwt.JWTUtil;
import com.group.application.login.dto.CustomUserDetails;
import com.group.domain.hr.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collection;

@Controller
@RequestMapping("/board")
public class FileBoardController {

    private FileBoardService fileBoardService;


    public FileBoardController(FileBoardService fileBoardService) {
        this.fileBoardService = fileBoardService;
    }

    @GetMapping("/fileboardlist")
    public String boardView(Model model,
                            @RequestParam(value = "page", defaultValue = "0") int page,
                            @RequestParam(value = "size", defaultValue = "5") int size
                            ) {
        Pageable pageRequest = PageRequest.of(page, size);
        Page<FileBoardDTO> fileBoardDTO = fileBoardService.findAllByFileBoard(pageRequest);
        model.addAttribute("fileBoardDTO", fileBoardDTO);
        return "board/fileboardlist";
    }

    @GetMapping("/fileboardwrite")
    public String fileBoardWriteVie(Model model, FileBoardDTO fileBoardDTO) {
        model.addAttribute("fileBoardDTO", fileBoardDTO);
        return "board/fileboardwrite";
    }

    //TODO JWT 토큰 스크립트 헤더로 처리 하면 될듯
    @PostMapping("/fileboardwrite")
    public String fileBoardWrite(@RequestParam(name="file", required = false) MultipartFile file,
                                 FileBoardDTO fileBoardDTO) throws IOException {
          fileBoardService.saveFileBoard(fileBoardDTO, file);
          return "redirect:/board/fileboardlist";
    }

    @GetMapping("/fileboarddetailview")
    public String boardDetailView(Model model, @RequestParam("id") int id) {
        model.addAttribute("fileBoardDTO", fileBoardService.findByIdFileBoard(id));
        return "board/fileboarddetailview";
    }
}
