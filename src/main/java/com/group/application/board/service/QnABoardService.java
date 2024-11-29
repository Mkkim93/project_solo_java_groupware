package com.group.application.board.service;

import com.group.application.board.dto.BoardDTO;
import com.group.application.board.dto.QnABoardDTO;
import com.group.domain.board.entity.Board;
import com.group.domain.board.entity.QnABoard;
import com.group.domain.board.repository.BoardRepositoryImpl;
import com.group.domain.board.repository.QnABoardRepository;
import com.group.exception.CustomException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class QnABoardService {

    private final BoardService boardService;
    private final BoardRepositoryImpl boardRepositoryImpl;
    private final QnABoardRepository qnABoardRepository;

    public Page<QnABoardDTO> findAll(Pageable pageable) {
        return boardRepositoryImpl.findAllByQnABoard(pageable);
    }

    public QnABoardDTO findByOne(Integer id, String boardPass) {
        QnABoardDTO dto = boardRepositoryImpl.findByOneQnABoard(id, boardPass);
        boardService.plusViewCount(dto.getBoardId());
        return dto;
    }

    public QnABoardDTO findByOnlyId(Integer id) {
        QnABoard qnABoard = qnABoardRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("not id"));

        QnABoardDTO dto = new QnABoardDTO();
        dto.setBoardId(qnABoard.getBoard().getId());
        return dto;
    }

    public QnABoardDTO findByIdNotPass(Integer id) {
        QnABoard qnABoard = qnABoardRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("no id"));
        return boardRepositoryImpl.findByOneQnABoardNotPass(qnABoard.getId());
    }

    public QnABoardDTO findById(Integer id, String boardPass) {
        QnABoard qnABoard = qnABoardRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("not id"));

        if (qnABoard.getBoardPass().equals(" ")) {
            return boardRepositoryImpl.findByOneQnABoardNotPass(id);
        }
        if (!qnABoard.getBoardPass().equals(boardPass)) {
            log.info("비밀번호가 다릅니다");
            throw new CustomException("fail password!");
        }
        return boardRepositoryImpl.findByOneQnABoard(id, boardPass);
    }

    public void save(QnABoardDTO dto) {
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.convertToQnaBoardDto(dto);
        Board board = boardService.saveAll(boardDTO);
        dto.setId(board.getId());
        qnABoardRepository.save(toEntity(dto, board));
    }

    // TODO 비밀글 여부 default 적용되는지 확인 할 것
    private QnABoard toEntity(QnABoardDTO dto, Board boardId) {
        return QnABoard.builder()
                .id(dto.getId())
                .boardPass(dto.getBoardPass())
                .board(boardId)
                .boardSecret(dto.getBoardSecret())
                .build();
    }

    public void update(QnABoardDTO dto) {
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.convertToQnaBoardDto(dto);
        boardService.saveAll(boardDTO);
    }

    public void delete(Integer id) {
        boardService.delete(id);
    }
}
