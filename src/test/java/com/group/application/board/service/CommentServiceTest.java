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
    /*@Test
    public void findAllByComment() {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(33);
        commentService.SaveCommentBoard(commentDTO);
        List<CommentDTO> all = commentService.findAll(commentDTO.getBoardId());
        for (CommentDTO dto : all) {
            System.out.println("dto.getCBoardContent() = " + dto.getCBoardContent());
            System.out.println("dto.getEmpId() = " + dto.getEmpId());
        }
    }*/

    /*@Test
    public void findAllByComRepo() {
        Integer id = 125;
        List<CommentDTO> allByCommentBoardId = commentBoardRepository.findAllByCommentBoardId(id);
        for (CommentDTO commentDTO : allByCommentBoardId) {
            System.out.println("commentDTO.getId() = " + commentDTO.getId());
            System.out.println("commentDTO.getBoardId() = " + commentDTO.getBoardId());
            System.out.println("commentDTO.getEmpName() = " + commentDTO.getEmpName());
            System.out.println("commentDTO = " + commentDTO.getCBoardContent());
        }
    }*/

}