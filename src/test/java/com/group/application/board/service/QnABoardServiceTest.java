package com.group.application.board.service;

import com.group.application.board.dto.BoardDTO;
import com.group.application.board.dto.QnABoardDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class QnABoardServiceTest {

    @Autowired
    QnABoardService qnABoardService;

    @Test
    public void saveNoticeBoard() {
        QnABoardDTO qnABoardDTO = new QnABoardDTO();
        qnABoardDTO.setBoardTitle("비밀글 제목2");
        qnABoardDTO.setBoardContent("비밀글 내용2");
        qnABoardDTO.setQBoardPass("1234"); // 게시글 비밀번호
        qnABoardService.saveQnABoard(qnABoardDTO);
    }

    @Test
    public void findById() {
        QnABoardDTO qnABoardDTO = new QnABoardDTO();
        qnABoardDTO.setQBoardPass("1234");
        qnABoardDTO.setId(1);

        QnABoardDTO qnAAndBoardId = qnABoardService.findQnABoardByQnAAndBoardId(qnABoardDTO.getId(), qnABoardDTO.getQBoardPass());
        System.out.println(
                "qnAAndBoardId.getBoardTitle() + " + qnAAndBoardId.getBoardTitle() +
                "qnAAndBoardId.getQBoardPass() = " + qnAAndBoardId.getQBoardPass());
    }

}