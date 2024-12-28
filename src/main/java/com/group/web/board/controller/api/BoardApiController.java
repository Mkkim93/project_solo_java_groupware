package com.group.web.board.controller.api;

import com.group.application.board.dto.NoticeBoardDTO;
import com.group.application.board.service.BoardService;
import com.group.application.board.service.NoticeBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BoardApiController {

    private final NoticeBoardService noticeBoardService;

    @GetMapping("/api/notice")
    public ResponseEntity<Page<NoticeBoardDTO>> mainNoticeView() {
        Page<NoticeBoardDTO> result = noticeBoardService.findMainNoticeContent();
        return ResponseEntity.ok(result);
    }

}
