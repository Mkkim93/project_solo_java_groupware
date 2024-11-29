package com.group.web.board.controller;

import com.group.application.board.dto.CommentDTO;
import com.group.application.board.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/save")
    public ResponseEntity save(@RequestParam(value = "page", defaultValue = "0") int page,
                               @RequestParam(value = "size", defaultValue = "10") int size,
                               @ModelAttribute CommentDTO commentDTO) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Integer saveResult = commentService.save(commentDTO);

        if (saveResult != null) {
            // 작성 성공하면 댓글 목록을 가져와서 리턴
            // 댓글 목록 : 해당 게시글의 댓글 전체
            Page<CommentDTO> commentDtOList = commentService.findAll(commentDTO.getBoardId(), pageRequest);
            return new ResponseEntity<>(commentDtOList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("해당 게시글이 존재하지 않습니다. ", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/list")
    public ResponseEntity<Page<CommentDTO>> commentsAny(@RequestParam("boardId") Integer boardId,
                                                        @RequestParam(value = "page", defaultValue = "0") int page,
                                                        @RequestParam(value = "size", defaultValue = "10") int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<CommentDTO> CommentDto = commentService.findAll(boardId, pageRequest);
        return new ResponseEntity<>(CommentDto, HttpStatus.OK);
    }

    @GetMapping("/lists")
    public ResponseEntity<Page<CommentDTO>> commentsQna(@RequestParam("boardId") Integer boardId,
                                                        @RequestParam("boardPass") String boardPass,
                                                        @RequestParam(value = "page", defaultValue = "0") int page,
                                                        @RequestParam(value = "size", defaultValue = "10") int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<CommentDTO> CommentDto = commentService.findAllQna(boardId, boardPass, pageRequest);
        return new ResponseEntity<>(CommentDto, HttpStatus.OK);
    }
}
