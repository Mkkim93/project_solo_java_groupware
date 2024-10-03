package com.group.application.board.service;

import com.group.application.board.dto.BoardDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class NoticeBoardServiceTest {

    @Autowired
    NoticeBoardService noticeBoardService;

    @Test
    public void saveNoticeBoard() {
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setBoardTitle("공지 테스트 제목 입니다");
        boardDTO.setBoardContent("공지 테스트 내용 입니다.");
    }
}