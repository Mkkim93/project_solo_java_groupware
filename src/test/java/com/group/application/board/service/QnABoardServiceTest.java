package com.group.application.board.service;

import com.group.application.board.dto.BoardDTO;
import com.group.application.board.dto.QnABoardDTO;
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
        System.out.println("qnABoard.getQBoardPass() = " + qnABoard.getBoardPass());
    }

    @Test
    public void findByOnly() {
        Integer id = 1;
        String qBoardPass = "1234";
        QnABoardDTO byIdOnly = qnABoardService.findByOne(id, qBoardPass);
        System.out.println("byIdOnly.getId() = " + byIdOnly.getId());
        System.out.println("byIdOnly.getBoardId() = " + byIdOnly.getBoardId());
        System.out.println("byIdOnly.getBoardTitle() = " + byIdOnly.getBoardTitle());
        System.out.println("byIdOnly.getBoardId = " + byIdOnly.getBoardId());
    }

    @Test
    public void deleteTest() {
        Integer id = 1;
        QnABoardDTO qnABoardDTO = new QnABoardDTO();
        qnABoardDTO.setId(id);

        BoardDTO boardDTO = new BoardDTO();
        qnABoardService.delete(qnABoardDTO.getBoardId());

        System.out.println("qnABoardDTO.getBoardId() = " + qnABoardDTO.getBoardId());
        System.out.println("qnABoardDTO.getIsDeleted() = " + qnABoardDTO.getIsDeleted());

        QnABoardDTO byIdOne = qnABoardService.findByOnlyId(id);
        System.out.println("byIdOne.getId() = " + byIdOne.getId());
        System.out.println("byIdOne.getBoardId() = " + byIdOne.getBoardId());
    }

    @Test
    void findByIdQnaBoard() {
        Integer id = 1;
        String boardPass = "1234";
        QnABoardDTO byId = qnABoardService.findById(id, boardPass);
        System.out.println("byId.getBoardId() = " + byId.getBoardId());
        System.out.println("byId.getId() = " + byId.getId());
    }

    @Test
    void notPassWorkBoardDetail() {
        QnABoardDTO dto = new QnABoardDTO();
        dto.setId(5);
        QnABoardDTO result = qnABoardService.findByIdNotPass(dto.getId());
        Stream.of(result).forEach(System.out::println);
    }

    @Test
    void findIdBuilder() {
        Integer id = 5;
        QnABoardDTO result = qnABoardService.findByOne(id, null);
        System.out.println("result.getBoardId() = " + result.getBoardId());
        System.out.println("result.getBoardPass() = " + result.getBoardPass());
        System.out.println("result.getBoardTitle() = " + result.getBoardTitle());
        System.out.println("result.getBoardContent() = " + result.getBoardContent());
    }

    @Test
    void findByOnlyId() {
        QnABoardDTO result = qnABoardService.findByOnlyId(5);
        System.out.println("result.getId() = " + result.getId());
        System.out.println("result.getBoardPass() = " + result.getBoardPass());
        System.out.println("result.getBoardId() = " + result.getBoardId());
    }

}