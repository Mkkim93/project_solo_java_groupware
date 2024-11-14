package com.group.application.board.service;

import com.group.application.board.dto.BoardDTO;
import com.group.application.board.dto.QnABoardDTO;
import com.group.domain.board.entity.Board;
import com.group.domain.board.entity.QnABoard;
import com.group.domain.board.repository.BoardRepositoryImpl;
import com.group.domain.board.repository.QnABoardRepository;
import com.group.exception.CustomException;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
public class QnABoardService {

    private final QnABoardRepository qnABoardRepository;
    private final BoardService boardService;
    private final BoardRepositoryImpl boardRepositoryImpl;

    @Autowired
    public QnABoardService(QnABoardRepository qnABoardRepository,
                           BoardService boardService,
                           BoardRepositoryImpl boardRepositoryImpl) {
        this.qnABoardRepository = qnABoardRepository;
        this.boardService = boardService;
        this.boardRepositoryImpl = boardRepositoryImpl;
    }

    public void saveQnABoard(QnABoardDTO qnABoardDTO) {
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.qnaConverterBoard(qnABoardDTO);
        Board boardId = boardService.saveProcessAllBoard(boardDTO);
        qnABoardDTO.setId(boardId.getId());
        QnABoard qnaEntity = getEntity(qnABoardDTO, boardId);
        qnABoardRepository.save(qnaEntity);
    }

    // TODO 비밀글 여부 default 적용되는지 확인 할 것
    private QnABoard getEntity(QnABoardDTO qnABoardDTO, Board boardId) {
        return QnABoard.builder()
                .id(qnABoardDTO.getId())
                .boardPass(qnABoardDTO.getBoardPass())
                .boardId(boardId)
                .boardSecret(qnABoardDTO.getBoardSecret())
                .build();
    }

    public Page<QnABoardDTO> findAllByQnABoard(Pageable pageable) {
        return boardRepositoryImpl.findAllByQnABoard(pageable);
    }

    public QnABoardDTO findByIdOnly(Integer id, String boardPass) {
        QnABoardDTO qnABoardDTO = boardRepositoryImpl.findByIdQnABoard(id, boardPass);
        boardService.updateBoardViewCount(qnABoardDTO.getBoardId());
        return qnABoardDTO;
    }

    public void updateQnABoard(QnABoardDTO qnABoardDTO) {
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.qnaConverterBoard(qnABoardDTO);
        boardService.saveProcessAllBoard(boardDTO);
    }

    public QnABoardDTO findById(Integer id, String boardPass) {
        QnABoard qnABoard = qnABoardRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("not id"));
        if (qnABoard.getBoardPass().equals(" ")) {
            return boardRepositoryImpl.findByIdQnABoardNotPass(id);
        }
        if (!qnABoard.getBoardPass().equals(boardPass)) {
            log.info("비밀번호가 다릅니다");
            throw new CustomException("fail password!");
        }
        return boardRepositoryImpl.findByIdQnABoard(id, boardPass);
    }

    public QnABoardDTO findByIdOne(Integer id) {
        QnABoard qnABoard = qnABoardRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("not id"));
        QnABoardDTO qnABoardDTO = new QnABoardDTO();
        qnABoardDTO.setBoardId(qnABoard.getBoardId().getId());
        return qnABoardDTO;
    }

    public void deleteBoard(Integer id) {
        boardService.deleteBoard(id);
    }

    public Boolean existIdAndBoardPass(Integer id, String boardPass) {
        QnABoard qnABoard = qnABoardRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("no id"));
        if (qnABoard.getBoardPass().equals(boardPass)) {
            log.info("비밀번호가 다릅니다.");
            return false;
        }
        return true;
    }

    public QnABoardDTO findByIdNotPass(Integer id) {
        QnABoard qnABoard = qnABoardRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("no id"));
        return boardRepositoryImpl.findByIdQnABoardNotPass(qnABoard.getId());
    }
}
