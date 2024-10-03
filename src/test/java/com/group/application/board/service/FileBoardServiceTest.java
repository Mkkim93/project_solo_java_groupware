package com.group.application.board.service;

import com.group.application.board.dto.BoardDTO;
import com.group.application.board.dto.FileBoardDTO;
import com.group.application.board.dto.FreeBoardDTO;
import com.group.domain.board.entity.Board;
import com.group.domain.board.entity.FileBoard;
import com.group.domain.board.repository.FileBoardRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.hamcrest.Matchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class FileBoardServiceTest {

    @Autowired
    FileBoardService fileBoardService;

        @Test
        public void saveFileBoardTest() throws IOException {
            // 가짜 DTO 생성
            FileBoardDTO fileBoardDTO = new FileBoardDTO();
            fileBoardDTO.setFBoardName("song.jpg");
            fileBoardDTO.setFBoardType("jpg");
            fileBoardDTO.setFBoardSize(1024L);
            fileBoardDTO.setFBoardPath("C:\\Users\\rainb\\OneDrive\\바탕 화면\\test\\song.jpg");

            fileBoardDTO.setBoardTitle("파일 제목 테스트");
            fileBoardDTO.setBoardContent("파일 내용 테스트");

            // 외부 경로에 있는 파일을 읽어서 MockMultipartFile로 변환
            String filePath = "C:\\Users\\rainb\\OneDrive\\바탕 화면\\test\\song.jpg"; // 실제 파일 경로
            FileInputStream inputFile = new FileInputStream(new File(fileBoardDTO.getFBoardPath()));

            MockMultipartFile mockFile = new MockMultipartFile(
                    "file", // 파라미터 이름
                    "song.jpg", // 파일 이름
                    "image/jpg", // 파일 타입
                    inputFile // 실제 파일 내용
            );
            fileBoardService.saveFileBoard(fileBoardDTO, mockFile);
        }

        @Test
        public void findByFileBoard() {
            FileBoardDTO byIdFileBoard = fileBoardService.findByIdFileBoard(15);
            System.out.println("게시판 제목 : " + byIdFileBoard.getBoardTitle() + ", 파일 이름 : " + byIdFileBoard.getFBoardName() +
                    ", 파일 경로 :  " + byIdFileBoard.getFBoardPath() + ", " + byIdFileBoard.getId());
        }
    }

