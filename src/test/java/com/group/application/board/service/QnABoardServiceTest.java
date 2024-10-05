package com.group.application.board.service;

import com.group.application.board.dto.BoardDTO;
import com.group.application.board.dto.QnABoardDTO;
import com.group.domain.board.entity.NoticeBoard;
import com.group.domain.board.entity.QnABoard;
import com.group.domain.board.repository.QnABoardRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Stream;

@SpringBootTest
class QnABoardServiceTest {

    @Autowired
    QnABoardService qnABoardService;

    @Autowired
    QnABoardRepository qnABoardRepository;



    @Test
    public void findById() {
        QnABoard qnABoard = qnABoardRepository.findById(1).get();
        System.out.println("qnABoard.getId() = " + qnABoard.getId());
        System.out.println("qnABoard.getQBoardPass() = " + qnABoard.getQBoardPass());
    }

    @Test
    public void findByOnly() {
        Integer id = 2;
        QnABoardDTO byIdOnly = qnABoardService.findByIdOnly(id);
        System.out.println("byIdOnly.getId() = " + byIdOnly.getId());
        System.out.println("byIdOnly.getBoardId() = " + byIdOnly.getBoardId());
        System.out.println("byIdOnly.getBoardTitle() = " + byIdOnly.getBoardTitle());
        System.out.println("byIdOnly.getBoardId = " + byIdOnly.getBoardId());
    }
}