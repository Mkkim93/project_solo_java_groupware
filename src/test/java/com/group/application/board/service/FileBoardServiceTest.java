package com.group.application.board.service;

import com.group.application.board.dto.FileBoardDTO;
import com.group.domain.board.entity.Board;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FileBoardServiceTest {

    @Autowired
    FileBoardService fileBoardService;

    @Test
    public void findByIdViewCount() {
        FileBoardDTO fileBoardDTO = new FileBoardDTO();
        fileBoardDTO.setId(74);
        fileBoardService.findByOne(fileBoardDTO.getId());
        Board board = new Board();

        board.setId(fileBoardDTO.getFileBoardId());
        System.out.println("board.getBoardTitle() = " + board.getBoardTitle());
        System.out.println("board.getBoardContent() = " + board.getBoardContent());
        System.out.println("board.getBoardViewCount() = " + board.getBoardViewCount());
    }

    @Test
    void fileSearchByFileContent() {
        FileBoardDTO dto = fileBoardService.findById(82);
        System.out.println("dto.getId() = " + dto.getId()); // 81
        System.out.println("dto.getBoardId() = " + dto.getBoardId()); // 165

    }
}

