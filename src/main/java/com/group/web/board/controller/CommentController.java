package com.group.web.board.controller;

import com.group.application.board.dto.CommentDTO;
import com.group.application.board.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Slf4j
@Controller
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/save")
    public ResponseEntity save(@ModelAttribute CommentDTO commentDTO,
                               @RequestParam(value = "page", defaultValue = "0") int page,
                               @RequestParam(value = "size", defaultValue = "10") int size) {
        PageRequest pageRequest = PageRequest.of(page, size);

        Integer saveResult = commentService.SaveCommentBoard(commentDTO);

        if (saveResult != null) {
            // 작성 성공하면 댓글 목록을 가져와서 리턴
            // 댓글 목록 : 해당 게시글의 댓글 전체
            Page<CommentDTO> commentDTOList = commentService.findAll(commentDTO.getBoardId(), pageRequest);
            return new ResponseEntity<>(commentDTOList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("해당 게시글이 존재하지 않습니다. ", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/list")
    public ResponseEntity<Page<CommentDTO>> listComments(@RequestParam("boardId") Integer boardId,
                                                         @RequestParam(value = "page", defaultValue = "0") int page,
                                                         @RequestParam(value = "size", defaultValue = "10") int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<CommentDTO> CommentDTO = commentService.findAll(boardId, pageRequest);
        return new ResponseEntity<>(CommentDTO, HttpStatus.OK);
    }

    @GetMapping("/lists")
    public ResponseEntity<Page<CommentDTO>> listCommentsQna(@RequestParam("boardId") Integer boardId,
                                                            @RequestParam("boardPass") String boardPass,
                                                            @RequestParam(value = "page", defaultValue = "0") int page,
                                                            @RequestParam(value = "size", defaultValue = "10") int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<CommentDTO> CommentDTO = commentService.findAllQna(boardId, boardPass, pageRequest);
        return new ResponseEntity<>(CommentDTO, HttpStatus.OK);
    }
}
