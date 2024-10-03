package com.group.application.board.service;

import com.group.application.board.dto.BoardDTO;
import com.group.application.board.dto.FreeBoardDTO;
import com.group.application.jwt.JWTUtil;
import com.group.application.login.dto.CustomUserDetails;
import com.group.domain.board.entity.Board;
import com.group.domain.board.entity.FreeBoard;
import com.group.domain.board.repository.BoardRepositoryImpl;
import com.group.domain.board.repository.FreeBoardRepository;
import com.group.domain.hr.entity.Employee;
import io.jsonwebtoken.Jwt;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    public FreeBoardDTO findByIdFreeBoard(Integer id) {
        freeBoardRepository.updateBoardViewCount(id);
        return boardRepositoryImpl.findByIdFreeBoard(id);
    }

}
