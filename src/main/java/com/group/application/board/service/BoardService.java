package com.group.application.board.service;

import com.group.application.board.dto.BoardDTO;
import com.group.application.cookie.service.CookieService;
import com.group.domain.board.entity.Board;
import com.group.domain.board.repository.BoardRepository;
import com.group.domain.board.repository.BoardRepositoryImpl;
import com.group.domain.hr.entity.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepositoryImpl boardRepositoryImpl;
    private final BoardRepository boardRepository;

    public Page<BoardDTO> findAll(Pageable pageable) {
        return boardRepositoryImpl.findAllByBoard(pageable);
    }

    public BoardDTO findByOne(Integer id) {
        boardRepository.plusViewCount(id);
        return boardRepositoryImpl.findByOneBoard(id);
    }

    // 조회수 올리지 않고 id 만 검색
    // all board 제외 controller 에서 사용
    public BoardDTO findByOnlyId(Integer id) {
        return boardRepository.findByOneBoard(id);
    }

    // 단순 ID 검색할 때 사용 (조회수 관련 없는 메서드)
    // all board controller 에서 사용
    public BoardDTO getBoardById(Integer id) {
        BoardDTO dto = new BoardDTO();
        Board board = boardRepository.getBoardById(id);
        return dto.toDto(board);
    }

    public Board saveAll(BoardDTO dto) {
        Board board = convertToBoard(dto);
        board.setEmployee(
                Employee.builder()
                .id(dto.getEmployee().getId())
                .build());
        return boardRepository.save(board);
    }

    public BoardDTO update(BoardDTO dto) {
        Board entity = boardRepository.save(convertToBoard(dto));
        return dto.toDto(entity);
    }

    public void delete(Integer id) {
        boardRepository.delete(id);
    }

    public void plusViewCount(Integer id) {
        boardRepository.plusViewCount(id);
    }

    private Board convertToBoard(BoardDTO dto) {
        return Board.builder()
                .id(dto.getId())
                .employee(Employee.builder()
                        .id(dto.getEmployee().getId())
                        .build())
                .boardTitle(dto.getBoardTitle())
                .boardContent(dto.getBoardContent())
                .build();
    }
}
