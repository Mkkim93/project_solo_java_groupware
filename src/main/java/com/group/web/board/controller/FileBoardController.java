package com.group.web.board.controller;

import com.group.application.board.dto.FileBoardDTO;
import com.group.application.board.service.FileBoardService;
import com.group.application.file.FileBoardStoreDTO;
import com.group.application.file.FileStoreService;
import com.group.application.file.UploadFile;
import com.group.domain.board.entity.FileBoard;
import com.group.domain.file.entity.FileStore;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriUtils;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static com.group.domain.file.entity.QFileStore.fileStore;

@Slf4j
@Controller
@RequestMapping("/board")
public class FileBoardController {

    private FileBoardService fileBoardService;
    private FileStoreService fileStoreService;

    public FileBoardController(FileBoardService fileBoardService,
                               FileStoreService fileStoreService) {
        this.fileBoardService = fileBoardService;
        this.fileStoreService = fileStoreService;

    }

    @GetMapping("/fileboardlist")
    public String boardView(Model model,
                            @RequestParam(value = "page", defaultValue = "0") int page,
                            @RequestParam(value = "size", defaultValue = "10") int size
                            ) {
        Pageable pageRequest = PageRequest.of(page, size);
        Page<FileBoardDTO> fileBoardDTO = fileBoardService.findAllByFileBoard(pageRequest);
        model.addAttribute("fileBoardDTO", fileBoardDTO);
        return "board/fileboardlist";
    }

    @GetMapping("/fileboardwrite")
    public String fileBoardWriteView(Model model, FileBoardDTO fileBoardDTO) {
        model.addAttribute("fileBoardDTO", fileBoardDTO);
        return "board/fileboardwrite";
    }

    //TODO JWT 토큰 스크립트 헤더로 처리 하면 될듯
    @PostMapping("/fileboardwrite")
    public String fileBoardWrite(@RequestParam(name = "file", required = false) List<MultipartFile> files,
                                 @ModelAttribute FileBoardDTO fileBoardDTO) throws IOException {
        fileBoardService.saveFileBoard(fileBoardDTO, files);
        return "redirect:/board/fileboardlist";
    }

    // TODO
    @GetMapping("/fileboarddetailview")
    public String boardDetailView(Model model, @RequestParam Integer id) {
        model.addAttribute("fileBoardDTO", fileBoardService.findByIdFileBoard(id));
        model.addAttribute("fileBoardStoreDTO", fileStoreService.findByStoreId(id));
        return "/board/fileboarddetailview";
    }

    @GetMapping("/fileboardmodify/{id}")
    public String boardModifyView(Model model, @PathVariable("id") Integer id) {
        FileBoardDTO fileBoardDTO = fileBoardService.findById(id);
        List<FileBoardStoreDTO> fileBoardStoreDTO = fileStoreService.findByStoreId(id);
        model.addAttribute("fileBoardDTO", fileBoardDTO);
        model.addAttribute("fileBoardStoreDTO", fileBoardStoreDTO);
        return "/board/fileboardmodify";  // 수정 페이지로 이동
    }

    @PostMapping("/fileboardmodify/update/{id}")
    public String boardModifyWriting(@ModelAttribute FileBoardDTO fileBoardDTO,
                                     @RequestParam(name = "file", required = false) List<MultipartFile> files,
                                     @PathVariable("id") Integer id, Model model) throws IOException {
        // 기존 파일 게시글 정보 가져오기
        FileBoardDTO fileBoardTemp = fileBoardService.findById(id);

        // 파일 게시글 정보 업데이트
        fileBoardTemp.setBoardTitle(fileBoardDTO.getBoardTitle());
        fileBoardTemp.setBoardContent(fileBoardDTO.getBoardContent());


        List<FileBoardStoreDTO> fileBoardStoreDTO = fileStoreService.findByStoreId(id);
        model.addAttribute("fileBoardStoreDTO", fileBoardStoreDTO);

        // 새로운 파일 업로드 처리
        if (files != null || files.isEmpty()) {
            fileBoardService.saveFileBoard(fileBoardTemp, files);
        }
        else {
            fileBoardService.updateFileBoard(fileBoardTemp);
        }

        return "redirect:/board/fileboardlist";  // 수정 후 목록 페이지로 리다이렉트
    }

    @GetMapping("/fileboarddetailview/delete/{id}")
    public String deleteBoard(@PathVariable("id") Integer id) {
        FileBoardDTO fileBoardDTO = fileBoardService.findById(id);
        fileBoardService.deleteBoard(fileBoardDTO.getBoardId());
        return "redirect:/board/fileboardlist";
    }

    /*@ResponseBody
    @GetMapping("/images/{filename}")
    public Resource downloadImage(@PathVariable String filename) throws MalformedURLException {
        return new UrlResource("file:" + fileStoreService.getFullPath(filename));
    }*/

    // 파일 다운로드
    @GetMapping("/download/{id}")
    public ResponseEntity<Resource> downloadFile(@PathVariable("id") Integer id) throws MalformedURLException {
        // 파일 정보 가져오기
        FileStore fileBoardStore = fileStoreService.findByIdOnly(id);

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
