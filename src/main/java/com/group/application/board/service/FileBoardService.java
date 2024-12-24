package com.group.application.board.service;

import com.group.application.board.dto.BoardDTO;
import com.group.application.board.dto.FileBoardDTO;
import com.group.application.file.FileStoreService;
import com.group.domain.board.entity.Board;
import com.group.domain.board.entity.FileBoard;
import com.group.domain.board.repository.BoardRepositoryImpl;
import com.group.domain.board.repository.FileBoardRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class FileBoardService {

    private final BoardService boardService;
    private final BoardRepositoryImpl boardRepositoryImpl;
    private final FileStoreService fileStoreService;
    private final FileBoardRepository fileBoardRepository;

    public Page<FileBoardDTO> findByAll(String searchKeyword, Pageable pageable) {
        return boardRepositoryImpl.findAllByFileBoard(searchKeyword, pageable);
    }

    public FileBoardDTO findByOne(Integer id) {
        FileBoardDTO dto = boardRepositoryImpl.findByOneFileBoard(id);
        boardService.plusViewCount(dto.getBoardId());
        return dto;
    }

    public FileBoardDTO findById(Integer id) {
        return boardRepositoryImpl.findByOneFileBoard(id);
    }

    public void save(FileBoardDTO dto, List<MultipartFile> file) throws IOException {

        // TODO 파일 저장 로직 별도 메서드로 구분 해야될 듯
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.convertToFileBoardDto(dto);
        Board board = boardService.saveAll(boardDTO);

        FileBoard fileBoard = toEntity(dto.getId(), board);
        fileBoardRepository.save(fileBoard);

        FileBoard fileBoardId = fileBoardRepository.findById(fileBoard.getId())
                .orElseThrow(() -> new EntityNotFoundException("no id"));

        fileStoreService.save(fileBoardId, file);
    }

    public void update(FileBoardDTO dto) {
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setBoardTitle(dto.getBoardTitle());
        boardDTO.setBoardContent(dto.getBoardContent());
        boardDTO.convertToFileBoardDto(dto);
        boardService.saveAll(boardDTO);
    }

    public void delete(Integer id) {
        boardService.delete(id);
    }

    // dto -> entity
    private FileBoard toEntity(Integer id, Board board) {
        return FileBoard.builder()
                .id(id)
                .board(board)
                .build();
    }
}
