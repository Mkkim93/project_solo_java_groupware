package com.group.application.board.service;

import com.group.application.board.dto.BoardDTO;
import com.group.application.board.dto.NoticeBoardDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class NoticeBoardServiceTest {

    @Autowired
    NoticeBoardService noticeBoardService;

    @Test
    public void save() {
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setBoardTitle("공지 테스트 제목 입니다");
        boardDTO.setBoardContent("공지 테스트 내용 입니다.");
    }

    @Test
    void findView() {
        Page<NoticeBoardDTO> mainNoticeContent = noticeBoardService.findMainNoticeContent();
        for (NoticeBoardDTO noticeBoardDTO : mainNoticeContent) {
            System.out.println("noticeBoardDTO = " + noticeBoardDTO.getBoardTitle());
            System.out.println("noticeBoardDTO.getBoardRegDate() = " + noticeBoardDTO.getBoardRegDate());
        }
    }
}
