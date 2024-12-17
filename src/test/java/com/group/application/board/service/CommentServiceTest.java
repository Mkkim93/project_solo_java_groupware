package com.group.application.board.service;

import com.group.application.board.dto.CommentDTO;
import com.group.domain.board.entity.CommentBoard;
import com.group.domain.board.repository.CommentBoardRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CommentServiceTest {

    @Autowired
    CommentService commentService;

    @Autowired
    CommentBoardRepository commentBoardRepository;

    // TODO

    public void findAllByComment() {
        PageRequest pageRequest = PageRequest.of(0, 10);
        Integer boardId = 4;
        String boardPass = "1234";
        Page<CommentDTO> allQna = commentService.findAllQna(boardId, pageRequest);
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
        Page<CommentDTO> allByCommentBoardId = commentBoardRepository.findByAllComment(id, pageRequest);
        for (CommentDTO commentDTO : allByCommentBoardId) {
            System.out.println("commentDTO.getId() = " + commentDTO.getId());
            System.out.println("commentDTO.getBoardId() = " + commentDTO.getBoardId());
            System.out.println("commentDTO.getComContent() = " + commentDTO.getComContent());
        }
    }

    @Test
    @DisplayName("댓글 업데이트")
    void updateComment() {
        CommentDTO comDto = new CommentDTO();
        comDto.setId(362);
        comDto.setComContent("댓글 수정 테스트");
        comDto.setEmpId(29);
        comDto.setBoardId(1);
        CommentDTO update = commentService.update(comDto);
        Stream.of(update).forEach(System.out::println);
        assertThat(update.getBoardId()).isEqualTo(comDto.getBoardId());
    }

    @Test
    @DisplayName("댓글 삭제")
    void deleteComment() {
        Integer commentId = 362;
        commentService.delete(commentId);
        CommentBoard commentBoard = commentBoardRepository.findById(commentId).get();
        assertThat(commentBoard.getComIsDeleted()).isEqualTo("Y");
    }

}