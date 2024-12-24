package com.group.domain.board.repository;

import com.group.domain.board.entity.Board;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BoardRepositoryTest {

    @Autowired
    BoardRepository boardRepository;

    @Test
    void findByBoardSearchKeyWord() {
        PageRequest pageRequest = PageRequest.of(0, 10);
        Page<Board> result = boardRepository.findByBoardTitleContaining("수정", pageRequest);
    }

}