package com.group.application.board.service;

import com.group.application.board.dto.BoardDTO;
import com.group.application.board.dto.FreeBoardDTO;
import com.group.domain.board.entity.Board;
import com.group.domain.board.entity.FreeBoard;
import com.group.domain.board.repository.FreeBoardRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FreeBoardServiceTest {

    @Autowired
    FreeBoardService freeBoardService;

    @Autowired
    FreeBoardRepository freeBoardRepository;

    @Test
    public void freeBoardSave() {
        FreeBoardDTO freeBoardDTO = new FreeBoardDTO();
        freeBoardDTO.setBoardTitle("제목 입니다");
        freeBoardDTO.setBoardContent("내용입니다.");
        freeBoardService.saveFreeBoard(freeBoardDTO);
    }

    @Test
    public void freeBoardIdSearch() {
        Integer id = 1;

    }

    @Test
    public void findById() {
        FreeBoard freeBoard = freeBoardRepository.findById(2).get();
        System.out.println("freeBoard.getBoardId() = " + freeBoard.getBoardId());
        System.out.println("freeBoard.getId() = " + freeBoard.getId());
        FreeBoardDTO freeBoardDTO = new FreeBoardDTO();
        freeBoardDTO.setDto(freeBoard);
        System.out.println("-----------------------------------");
        System.out.println("freeBoardDTO.getBoardId() = " + freeBoardDTO.getBoardId());
        System.out.println("freeBoardDTO.getId() = " + freeBoardDTO.getId());
    }

    @Test
    public void updateFreeBoard() {
        FreeBoardDTO freeBoardDTO = new FreeBoardDTO();
        Integer id = 6;
        Integer freeBoardId = freeBoardRepository.findBoardIdByFreeBoardId(id);
        freeBoardDTO.setId(id);
        freeBoardDTO.setBoardId(freeBoardId);
        freeBoardDTO.setBoardTitle("제목 수정 최종!!");
        freeBoardDTO.setBoardContent("내용 수정 최종!!");
        freeBoardService.updateFreeBoard(freeBoardDTO);
    }

    @Test
    public void findByIdUpdate() {
        FreeBoardDTO freeBoardDTO = new FreeBoardDTO();
        freeBoardDTO.setId(6);
        FreeBoardDTO byId = freeBoardService.findBoardIdByFreeBoardId(freeBoardDTO.getId());
        System.out.println("byId = " + byId.getId());
    }

    @Test
    public void freeBoardId() {
        FreeBoardDTO freeBoardDTO = new FreeBoardDTO();
        freeBoardDTO.setBoardId(6);
        Integer freeBoardId = freeBoardDTO.getBoardId();
        Integer id = freeBoardRepository.findBoardIdByFreeBoardId(freeBoardId);
        System.out.println("id = " + id);
    }

    @Test
    public void freeBoardIdV2() {
        FreeBoardDTO freeBoardDTO = new FreeBoardDTO();
        freeBoardDTO.setId(6);
        FreeBoardDTO boardIdByFreeBoardId = freeBoardService.findBoardIdByFreeBoardId(freeBoardDTO.getId());
        System.out.println("boardIdByFreeBoardId.getBoardId() = " + boardIdByFreeBoardId.getBoardId());
        freeBoardService.updateFreeBoard(freeBoardDTO);
    }


    @Test
    public void freeBoardFindByIdBoardList() {
        FreeBoardDTO byIdOnlyFreeBoard = freeBoardService.findByIdOnlyFreeBoard(4);
        System.out.println("byIdQnABoard.getId() = " + byIdOnlyFreeBoard.getId());
        System.out.println("byIdQnABoard.getBoardTitle() = " + byIdOnlyFreeBoard.getBoardTitle());
        System.out.println("byIdQnABoard.getBoardContent() = " + byIdOnlyFreeBoard.getBoardContent());
        System.out.println("byIdQnABoard.getBoardId() = " + byIdOnlyFreeBoard.getBoardId());
    }

    @Test
    public void findByFreeBoardBoardId() {
        Integer id = 13;
        Integer freeBoardByBoardId = freeBoardRepository.findFreeBoardByBoardId(id);
        System.out.println("freeBoardByBoardId = " + freeBoardByBoardId);

        FreeBoardDTO byIdOnlyFreeBoard = freeBoardService.findByIdOnlyFreeBoard(id);
        System.out.println("byIdOnlyFreeBoard.getBoardId() = " + byIdOnlyFreeBoard.getBoardId());
    }
}