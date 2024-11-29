package com.group.application.board.service;

import com.group.application.board.dto.BoardDTO;
import com.group.application.board.dto.FreeBoardDTO;
import com.group.domain.board.entity.Board;
import com.group.domain.board.entity.FreeBoard;
import com.group.domain.board.repository.BoardRepositoryImpl;
import com.group.domain.board.repository.FreeBoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class FreeBoardService {

    private final BoardService boardService;
    private final FreeBoardRepository freeBoardRepository;
    private final BoardRepositoryImpl boardRepositoryImpl;

    public Page<FreeBoardDTO> findAll(Pageable pageable) {
        return boardRepositoryImpl.findAllByFreeBoard(pageable);
    }

    public FreeBoardDTO findByOne(Integer id) {
        FreeBoardDTO freeBoardDTO = boardRepositoryImpl.findByOneFreeBoard(id);
        boardService.plusViewCount(freeBoardDTO.getBoardId());
        return freeBoardDTO;
    }

    public FreeBoardDTO findByOnlyId(Integer id) {
        FreeBoardDTO dto = new FreeBoardDTO();
        Integer boardId = freeBoardRepository.findByFreeBoardId(id);
        dto.setId(id);
        dto.setBoardId(boardId);
        return dto;
    }

    public FreeBoardDTO findById(Integer id) {
        return boardRepositoryImpl.findByOneFreeBoard(id);
    }

    public void save(FreeBoardDTO dto) {
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.convertToFreeBoardDto(dto);
        Board boardId = boardService.saveAll(boardDTO);
        FreeBoard entity = convertEntity(boardId);
        freeBoardRepository.save(entity);
    }

    public void update(FreeBoardDTO freeBoardDTO) {
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.convertToFreeBoardDto(freeBoardDTO);
        boardService.saveAll(boardDTO);
    }

    public void delete(Integer id) {
        boardService.delete(id);
    }

    private FreeBoard convertEntity(Board board) {
        return FreeBoard.builder()
                .board(board)
                .build();
    }
}
