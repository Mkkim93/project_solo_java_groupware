package com.group.web.board.controller;

import com.group.application.board.dto.CommentDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CommentControllerTest {

    @Autowired
    CommentController commentController;

    @Test
    @DisplayName("컨트롤러 댓글 수정")
    void updateComment() {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(365);
        commentDTO.setBoardId(1);
        commentDTO.setComContent("댓글 수정 comId : 365");
        commentDTO.setEmpId(29);
        ResponseEntity update = commentController.update(commentDTO);
        System.out.println("update.toString() = " + update.toString());
    }
}