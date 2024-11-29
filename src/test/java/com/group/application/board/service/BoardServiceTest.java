package com.group.application.board.service;

import com.group.application.board.dto.BoardDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BoardServiceTest {

    @Autowired
    BoardService boardService;

    @Test
    public void findByBoard() {
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setId(13);
        BoardDTO id = boardService.findByOne(boardDTO.getId());
        System.out.println(id.getBoardTitle() + " " + id.getBoardContent());
        System.out.println("id.getId() = " + id.getId());
        System.out.println("id.getBoardId() = " + id.getBoardId());
    }

    @Test
    public void update() {
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setId(50);
        boardDTO.setBoardTitle("게시글 수정");
        boardDTO.setBoardContent("게시글 내용 수정");
        boardService.update(boardDTO);
    }

    @Test
    public void delete() {
        Integer id = 115;
        boardService.delete(115);
        BoardDTO boardDTO = boardService.findByOne(115);
        System.out.println("boardDTO.getBoardDeleteDate() = " + boardDTO.getBoardDeleteDate());
        System.out.println("boardDTO.getIsDeleted() = " + boardDTO.getIsDeleted());
    }
}