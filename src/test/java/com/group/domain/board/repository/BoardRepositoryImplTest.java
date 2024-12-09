package com.group.domain.board.repository;

import com.group.application.board.dto.FileBoardDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BoardRepositoryImplTest {

    @Autowired BoardRepositoryImpl impl;

    @Test
    void findByFileBoardId() {
        FileBoardDTO result = impl.findByOneFileBoard(81);
        System.out.println("result.getBoardId() = " + result.getBoardId());
        System.out.println("result.getId() = " + result.getId());
        System.out.println("result.getFileBoardId() = " + result.getFileBoardId());
    }
}