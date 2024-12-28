package com.group.domain.board.repository;

import com.group.domain.board.entity.NoticeBoard;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class NoticeBoardRepositoryTest {

    @Autowired  NoticeBoardRepository noticeBoardRepository;

    @Test
    void findMainView() {



    }

}