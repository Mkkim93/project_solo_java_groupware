package com.group.application.board.service;

import com.group.application.board.dto.BoardDTO;
import com.group.application.board.dto.FileBoardDTO;
import com.group.domain.board.entity.Board;
import com.group.domain.board.entity.FileBoard;
import com.group.domain.board.repository.BoardRepository;
import com.group.domain.board.repository.BoardRepositoryImpl;
import com.group.domain.board.repository.FileBoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
@Transactional
public class FileBoardService {

    private final FileBoardRepository fileBoardRepository;
    private final BoardService boardService;
    private final BoardRepositoryImpl boardRepositoryImpl;

    @Autowired
    public FileBoardService(FileBoardRepository fileBoardRepository,
                            BoardService boardService,
                            BoardRepositoryImpl boardRepositoryImpl
    ) {
        this.fileBoardRepository = fileBoardRepository;
        this.boardService = boardService;
        this.boardRepositoryImpl = boardRepositoryImpl;
    }

    // TODO 파일 저장 로직 별도 메서드로 구분 해야될 듯
    // 파일 게시판 등록
    public void saveFileBoard(FileBoardDTO fileBoardDTO, MultipartFile file) throws IOException {

        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setBoardDTO(fileBoardDTO);
        Board boardId = boardService.saveProcessAllBoard(boardDTO);

        // 저장 경로 지정
        String projectPath = System.getProperty("user.dir") + "/src/main/resources/static/files";
        UUID uuid = UUID.randomUUID(); // 파일 이름에 붙일 랜덤명 설정
        String fileName = uuid + "_" + file.getOriginalFilename(); // 랜덤명 + 실제 파일명

        File saveFile = new File(projectPath, fileName);
        file.transferTo(saveFile);
        FileBoard fileBoard = getEntitySave(boardDTO, boardId);
        fileBoard.setFBoardName(fileName);
        fileBoard.setFBoardPath("/files/" + fileName);

        fileBoardRepository.save(fileBoard);
    }

    // dto -> entity
    private FileBoard getEntitySave(BoardDTO boardDTO, Board boardId) {

       return FileBoard.builder()
                .fBoardName(boardDTO.getFBoardName())
                .fBoardType(boardDTO.getFBoardType())
                .fBoardSize(boardDTO.getFBoardSize())
                .fBoardPath(boardDTO.getFBoardPath())
                .boardId(boardId)
                .build();
    }

    public Page<FileBoardDTO> findAllByFileBoard(Pageable pageable) {
        return boardRepositoryImpl.findAllByFileBoard(pageable);
    }

    public FileBoardDTO findByIdFileBoard(Integer id) {
        fileBoardRepository.updateBoardViewCount(id);
        return boardRepositoryImpl.findByIdFileBoard(id);
    }
}
