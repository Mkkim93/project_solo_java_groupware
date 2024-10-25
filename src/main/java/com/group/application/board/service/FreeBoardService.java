package com.group.application.board.service;

import com.group.application.board.dto.BoardDTO;
import com.group.application.board.dto.FreeBoardDTO;
import com.group.domain.board.entity.Board;
import com.group.domain.board.entity.FreeBoard;
import com.group.domain.board.repository.BoardRepositoryImpl;
import com.group.domain.board.repository.FreeBoardRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FreeBoardService {

    private final FreeBoardRepository freeBoardRepository;
    private final BoardService boardService;
    private final BoardRepositoryImpl boardRepositoryImpl;


    @Autowired
    public FreeBoardService(FreeBoardRepository freeBoardRepository,
                            BoardService boardService,
                            BoardRepositoryImpl boardRepositoryImpl
                            ) {
        this.freeBoardRepository = freeBoardRepository;
        this.boardService = boardService;
        this.boardRepositoryImpl = boardRepositoryImpl;
    }

    public void saveFreeBoard(FreeBoardDTO freeBoardDTO) {
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.freeConverterBoard(freeBoardDTO);
        Board boardId = boardService.saveProcessAllBoard(boardDTO);
        FreeBoard freeEntity = getEntity(boardId);
        freeBoardRepository.save(freeEntity);
    }

    private FreeBoard getEntity(Board boardId) {
        return FreeBoard.builder()
                .boardId(boardId)
                .build();
    }

    public Page<FreeBoardDTO> findAllByFreeBoard(Pageable pageable) {
        return boardRepositoryImpl.findAllByFreeBoard(pageable);
    }

    /*public FreeBoardDTO findByIdFreeBoard(Integer id) {
        return boardRepositoryImpl.findByIdFreeBoard(id);
    }*/

    public FreeBoardDTO findBoardIdByFreeBoardId(Integer id) {
        FreeBoardDTO freeBoardDTO = new FreeBoardDTO();
        Integer freeBoardId = freeBoardRepository.findBoardIdByFreeBoardId(id);
        freeBoardDTO.setId(id);
        freeBoardDTO.setBoardId(freeBoardId);
        return freeBoardDTO;
    }

    public FreeBoardDTO findByIdOnlyFreeBoard(Integer id) {
        FreeBoardDTO freeBoardDTO = boardRepositoryImpl.findByIdFreeBoard(id);
        boardService.updateBoardViewCount(freeBoardDTO.getBoardId());
        return freeBoardDTO;
    }

    public void updateFreeBoard(FreeBoardDTO freeBoardDTO) {
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.freeConverterBoard(freeBoardDTO);
        Board boardId = boardService.saveProcessAllBoard(boardDTO);
        FreeBoard setEntity = getSetEntity(freeBoardDTO, boardId);
        // freeBoardRepository.save(setEntity);
    }

    private FreeBoard getSetEntity(FreeBoardDTO freeBoardDTO, Board boardId) {
        return FreeBoard.builder()
                .id(freeBoardDTO.getId())
                .boardId(boardId)
                .build();
    }

    public FreeBoardDTO findById(Integer id) {
        return boardRepositoryImpl.findByIdFreeBoard(id);
    }

    public void deleteBoard(Integer id) {
        boardService.deleteBoard(id);
    }
}
