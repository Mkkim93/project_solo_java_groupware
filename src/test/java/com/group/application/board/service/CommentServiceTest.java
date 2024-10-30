package com.group.application.board.service;

import com.group.application.board.dto.CommentDTO;
import com.group.domain.board.repository.CommentBoardRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

@SpringBootTest
class CommentServiceTest {

    @Autowired
    CommentService commentService;

    @Autowired
    CommentBoardRepository commentBoardRepository;

    // TODO
    @Test
    public void findAllByComment() {
        PageRequest pageRequest = PageRequest.of(0, 10);
        Integer boardId = 4;
        String boardPass = "1234";
        Page<CommentDTO> allQna = commentService.findAllQna(boardId, boardPass, pageRequest);
        for (CommentDTO commentDTO : allQna) {
            System.out.println("commentDTO.getComContent() = " + commentDTO.getComContent());
            System.out.println("commentDTO.getBoardId() = " + commentDTO.getBoardId());
            System.out.println("commentDTO.getBoardPass() = " + commentDTO.getBoardPass());
            System.out.println("commentDTO.getId() = " + commentDTO.getId());

        }
    }

    @Test
    public void findAllByComRepo() {
        Integer id = 125;
        PageRequest pageRequest = PageRequest.of(0, 10);
        Page<CommentDTO> allByCommentBoardId = commentBoardRepository.findAllByCommentBoardId(id, pageRequest);
        for (CommentDTO commentDTO : allByCommentBoardId) {
            System.out.println("commentDTO.getId() = " + commentDTO.getId());
            System.out.println("commentDTO.getBoardId() = " + commentDTO.getBoardId());
            System.out.println("commentDTO.getComContent() = " + commentDTO.getComContent());
        }

    }

}