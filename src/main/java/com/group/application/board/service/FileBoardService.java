package com.group.application.board.service;

import com.group.application.board.dto.BoardDTO;
import com.group.application.board.dto.FileBoardDTO;
import com.group.application.file.FileStoreService;
import com.group.domain.board.entity.Board;
import com.group.domain.board.entity.FileBoard;
import com.group.domain.board.repository.BoardRepositoryImpl;
import com.group.domain.board.repository.FileBoardRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@Transactional
public class FileBoardService {

    private final FileBoardRepository fileBoardRepository;
    private final BoardService boardService;
    private final BoardRepositoryImpl boardRepositoryImpl;
    private final FileStoreService fileStoreService;

    @Autowired
    public FileBoardService(FileBoardRepository fileBoardRepository,
                            BoardService boardService,
                            BoardRepositoryImpl boardRepositoryImpl,
                            FileStoreService fileStoreService
    ) {
        this.fileBoardRepository = fileBoardRepository;
        this.boardService = boardService;
        this.boardRepositoryImpl = boardRepositoryImpl;
        this.fileStoreService = fileStoreService;
    }

    // TODO 파일 저장 로직 별도 메서드로 구분 해야될 듯
    // 파일 게시판 등록
    public void saveFileBoard(FileBoardDTO fileBoardDTO, List<MultipartFile> file) throws IOException {

        BoardDTO boardDTO = new BoardDTO();
        boardDTO.fileConverterBoard(fileBoardDTO);
        Board boardId = boardService.saveProcessAllBoard(boardDTO);

        FileBoard fileBoard = getEntitySave(fileBoardDTO.getId(), boardId);
        fileBoardRepository.save(fileBoard);

        FileBoard fileBoardId = fileBoardRepository.findById(fileBoard.getId())
                .orElseThrow(() -> new EntityNotFoundException("NO ID"));

            fileStoreService.fileStoreSave(fileBoardId, file);
    }

    // dto -> entity
    private FileBoard getEntitySave(Integer id, Board boardId) {
        return FileBoard.builder()
                .id(id)
                .boardId(boardId)
                .build();
    }

    public Page<FileBoardDTO> findAllByFileBoard(Pageable pageable) {
        return boardRepositoryImpl.findAllByFileBoard(pageable);
    }

    public FileBoardDTO findByIdFileBoard(Integer id) {
        FileBoardDTO fileBoardDTO = boardRepositoryImpl.findByIdFileBoard(id);
        boardService.updateBoardViewCount(fileBoardDTO.getBoardId());
        return fileBoardDTO;
    }

    public void updateFileBoard(FileBoardDTO fileBoardDTO) {
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setBoardTitle(fileBoardDTO.getBoardTitle());
        boardDTO.setBoardContent(fileBoardDTO.getBoardContent());
        boardDTO.fileConverterBoard(fileBoardDTO);
        boardService.saveProcessAllBoard(boardDTO);
    }

    public FileBoardDTO findById(Integer id) {
        return boardRepositoryImpl.findByIdFileBoard(id);
    }

    public void deleteBoard(Integer id) {
        boardService.deleteBoard(id);
    }
}
