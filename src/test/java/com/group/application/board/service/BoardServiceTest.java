package com.group.application.board.service;

import com.group.application.board.dto.BoardDTO;
import com.group.domain.board.entity.Board;
import com.group.domain.hr.entity.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BoardServiceTest {

    @Autowired
    BoardService boardService;

    @Test
    public void findByBoard() {
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setId(1);
        BoardDTO id = boardService.findById(1);
        System.out.println(id.getBoardTitle() + " " + id.getBoardContent());
    }

    @Test
    public void update() {
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setId(50);
        boardDTO.setBoardTitle("게시글 수정");
        boardDTO.setBoardContent("게시글 내용 수정");
        boardService.updateBoard(boardDTO);
    }
}