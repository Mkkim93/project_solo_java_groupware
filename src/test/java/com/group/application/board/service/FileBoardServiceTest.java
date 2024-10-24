package com.group.application.board.service;

import com.group.application.board.dto.FileBoardDTO;
import com.group.domain.board.entity.Board;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.mock.web.MockMultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@SpringBootTest
class FileBoardServiceTest {

    @Autowired
    FileBoardService fileBoardService;

    @Test
    public void findByIdViewCount() {
        FileBoardDTO fileBoardDTO = new FileBoardDTO();
        fileBoardDTO.setId(74);
        fileBoardService.findByIdFileBoard(fileBoardDTO.getId());
        Board board = new Board();

        board.setId(fileBoardDTO.getFileBoardId());
        System.out.println("board.getBoardTitle() = " + board.getBoardTitle());
        System.out.println("board.getBoardContent() = " + board.getBoardContent());
        System.out.println("board.getBoardViewCount() = " + board.getBoardViewCount());
    }
}

