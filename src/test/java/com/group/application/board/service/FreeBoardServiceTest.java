package com.group.application.board.service;

import com.group.application.board.dto.BoardDTO;
import com.group.application.board.dto.FreeBoardDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FreeBoardServiceTest {

    @Autowired
    FreeBoardService freeBoardService;

    @Test
    public void freeBoardSave() {
        FreeBoardDTO freeBoardDTO = new FreeBoardDTO();
        freeBoardDTO.setBoardTitle("제목 입니다");
        freeBoardDTO.setBoardContent("내용입니다.");
        freeBoardService.saveFreeBoard(freeBoardDTO);
    }
}