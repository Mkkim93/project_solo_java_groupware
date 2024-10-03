package com.group.application.board.service;

import com.group.application.board.dto.BoardDTO;
import com.group.application.login.dto.CustomUserDetails;
import com.group.domain.board.entity.Board;
import com.group.domain.board.repository.BoardRepository;
import com.group.domain.board.repository.BoardRepositoryImpl;
import com.group.domain.hr.entity.Employee;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
public class BoardService {

    private final BoardRepositoryImpl boardRepositoryImpl;
    private final BoardRepository boardRepository;
    private JPAQueryFactory jpaQueryFactory;


    public BoardService(BoardRepository boardRepository,
                        EntityManager entityManager,
                        BoardRepositoryImpl boardRepositoryImpl) {
        this.boardRepositoryImpl = boardRepositoryImpl;
        this.boardRepository = boardRepository;
        jpaQueryFactory = new JPAQueryFactory(entityManager);
    }

    public Board saveProcessAllBoard(BoardDTO boardDTO) {

        // TODO jwt 에서 토큰 정보 가져와야함 현재는 그냥 객체 생성해서 임시로 진행
        Employee employee = new Employee();
        boardDTO.setEmpId(employee);
        employee.setId(1);

        Board board = fromEntity(boardDTO);
        board.setEmpId(employee);
        board.setBoardRegDate(LocalDateTime.now());

        return boardRepository.save(board);
    }

    private Board fromEntity(BoardDTO boardDTO) {
        return Board.builder()
                .empId(boardDTO.getEmpId())
                .id(boardDTO.getId())
                .boardTitle(boardDTO.getBoardTitle())
                .boardContent(boardDTO.getBoardContent())
                .build();
    }

    public Page<BoardDTO> findAllByBoard(Pageable pageable) {
        return boardRepositoryImpl.findAllByBoard(pageable);
    }

    public BoardDTO findById(Integer id) {
        boardRepository.updateBoardViewCount(id);
        return boardRepositoryImpl.findByIdBoard(id);
    }

    public BoardDTO updateBoard(BoardDTO boardDTO) {
        Employee employee = new Employee();
        employee.setId(1);
        boardDTO.setEmpId(employee);
        Board entity = boardRepository.save(fromEntity(boardDTO));
        entity.setBoardUpdateDate(LocalDateTime.now());
        return boardDTO.fromDTO(entity);
    }

    public BoardDTO getBoardById(Integer id) {
        BoardDTO boardDTO = new BoardDTO();
        Board boardById = boardRepository.getBoardById(id);
        return boardDTO.fromDTO(boardById);
    }
}
