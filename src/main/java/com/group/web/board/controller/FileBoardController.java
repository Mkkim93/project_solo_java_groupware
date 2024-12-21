package com.group.web.board.controller;

import com.group.application.board.dto.FileBoardDTO;
import com.group.application.board.service.BoardService;
import com.group.application.board.service.CommentService;
import com.group.application.board.service.FileBoardService;
import com.group.application.file.FileBoardStoreDTO;
import com.group.application.file.FileStoreService;
import com.group.application.hr.dto.EmployeeDTO;
import com.group.application.hr.service.EmployeeService;
import com.group.domain.file.entity.FileStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriUtils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.util.List;


@Slf4j
@Controller
@RequestMapping("/board/file")
@RequiredArgsConstructor
public class FileBoardController {

    private final FileBoardService fileBoardService;
    private final FileStoreService fileStoreService;
    private final CommentService commentService;
    private final BoardService boardService;
    private final EmployeeService employeeService;

    @GetMapping("/list")
    public String view(@RequestParam(value = "page", defaultValue = "0") int page,
                       @RequestParam(value = "size", defaultValue = "15") int size,
                       Model model) {
        Pageable pageRequest = PageRequest.of(page, size);
        Page<FileBoardDTO> fileBoardDto = fileBoardService.findByAll(pageRequest);
        model.addAttribute("fileBoardList", fileBoardDto);
        return "board/file/list";
    }

    @GetMapping("/detail")
    public String detail(@RequestParam Integer id,
                         @RequestParam(value = "page", defaultValue = "0") int page,
                         @RequestParam(value = "size", defaultValue = "10") int size,
                         @CookieValue(value = "uuid") String empUUID, EmployeeDTO employeeDto,
                         Model model) {
        employeeDto.setEmpUUID(empUUID);
        model.addAttribute("employeeDto", employeeService.findByEmployee(employeeDto));
        PageRequest pageRequest = PageRequest.of(page, size);
        FileBoardDTO fileBoardDto = fileBoardService.findById(id);
        model.addAttribute("fileStoreDto", fileStoreService.findByStoreId(id));
        model.addAttribute("fileBoardDto", fileBoardService.findByOne(id));
        model.addAttribute("commentDto", commentService.findAll(fileBoardDto.getBoardId(), pageRequest));
        model.addAttribute("boardDto", boardService.findByOnlyId(fileBoardDto.getBoardId()));
        return "board/file/detail";
    }

    @GetMapping("/write")
    public String write(FileBoardDTO fileBoardDto, Model model) {
        model.addAttribute("fileBoardDto", fileBoardDto);
        model.addAttribute("fileStoreDto", fileStoreService.findByStoreId(fileBoardDto.getId()));
        return "board/file/write";
    }

    @PostMapping("/write")
    public String writeProc(@CookieValue(value = "uuid") String empUUID, EmployeeDTO employeeDto,
                            @RequestParam(name = "file", required = false) List<MultipartFile> files,
                            @ModelAttribute FileBoardDTO fileBoardDto, Model model) throws IOException {
        employeeDto.setEmpUUID(empUUID);
        fileBoardDto.setEmployee(employeeService.findByEmployee(employeeDto));
        fileBoardService.save(fileBoardDto, files);
        model.addAttribute("fileStoreDto", fileStoreService.findByStoreId(fileBoardDto.getId()));
        return "redirect:/board/file/list";
    }

    @GetMapping("/modify/{id}")
    public String modify(@PathVariable("id") Integer id, Model model) {
        FileBoardDTO fileBoardDto = fileBoardService.findById(id);
        List<FileBoardStoreDTO> fileBoardStoreDto = fileStoreService.findByStoreId(id);
        model.addAttribute("fileBoardDto", fileBoardDto);
        model.addAttribute("fileStoreDto", fileBoardStoreDto);
        return "board/file/modify";  // 수정 페이지로 이동
    }

    @PostMapping("/modify/update/{id}")
    public String modifyProc(@ModelAttribute FileBoardDTO fileBoardDto,
                             @RequestParam(name = "file", required = false) List<MultipartFile> files,
                             @PathVariable("id") Integer id, Model model, EmployeeDTO employeeDto,
                             @CookieValue(value = "uuid") String empUUID) throws IOException {

        // 사용자 정보 가져오기
        employeeDto.setEmpUUID(empUUID);
        // 기존 파일 게시글 정보 가져오기
        FileBoardDTO fileBoardTemp = fileBoardService.findById(id);
        // 파일 게시글 정보 업데이트
        fileBoardTemp.updateBoard(fileBoardDto, employeeService.findByEmployee(employeeDto));

        List<FileBoardStoreDTO> fileStoreDto = fileStoreService.findByStoreId(id); // == id
        model.addAttribute("fileStoreDto", fileStoreDto);

        // 새로운 파일 업로드 처리
        if (files != null || files.isEmpty()) {
            fileBoardService.save(fileBoardTemp, files);
        }
        else {
            fileBoardService.update(fileBoardTemp);
        }
        return "redirect:/board/file/list";  // 수정 후 목록 페이지로 리다이렉트
    }

    @GetMapping("/detail/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        FileBoardDTO fileBoardDto = fileBoardService.findById(id);
        fileBoardService.delete(fileBoardDto.getBoardId());
        return "redirect:/board/file/list";
    }

    // 파일 다운로드
    @GetMapping("/download/{id}")
    public ResponseEntity<Resource> download(@PathVariable("id") Integer id) throws MalformedURLException {
        // 파일 정보 가져오기
        FileStore fileBoardStore = fileStoreService.findById(id);

        if (fileBoardStore == null) {
            return ResponseEntity.notFound().build(); // 파일이 없을 경우 404 반환
        }

        String uploadFileName = fileBoardStore.getOriginFileName();
        String storeFileName = fileBoardStore.getFileName();

        // 파일 리소스 생성
        UrlResource urlResource = new UrlResource("file:" + fileStoreService.getFullPath(storeFileName));

        // 로그 확인
        log.info("urlResource={}", urlResource);
        log.info("fileName={}", uploadFileName);

        // 파일 이름 인코딩
        String encodedFileName = UriUtils.encode(uploadFileName, StandardCharsets.UTF_8);
        String contentDisposition = "attachment; filename=\"" + encodedFileName + "\"";

        // 파일 응답 반환
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
                .body(urlResource);
    }
}
