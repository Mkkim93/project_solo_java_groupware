package com.group.application.board.service;

import com.group.application.board.dto.BoardDTO;
import com.group.application.board.dto.QnABoardDTO;
import com.group.domain.board.entity.Board;
import com.group.domain.board.entity.QnABoard;
import com.group.domain.board.repository.BoardRepositoryImpl;
import com.group.domain.board.repository.QnABoardRepository;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class QnABoardService {

    private static final Logger log = LoggerFactory.getLogger(QnABoardService.class);
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
                .qBoardPass(qnABoardDTO.getQBoardPass())
                .boardId(boardId)
                .qBoardIsSecret(qnABoardDTO.getQBoardIsSecret())
                .build();
    }

    public Page<QnABoardDTO> findAllByQnABoard(Pageable pageable) {
        return boardRepositoryImpl.findAllByQnABoard(pageable);
    }

    public QnABoardDTO findByIdOnly(Integer id, String qBoardPass) {
        QnABoardDTO qnaBoardDTO = boardRepositoryImpl.findByIdQnABoard(id, qBoardPass);
        boardService.updateBoardViewCount(qnaBoardDTO.getBoardId());
        return qnaBoardDTO;
    }

    public void updateQnABoard(QnABoardDTO qnABoardDTO) {
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.qnaConverterBoard(qnABoardDTO);
        boardService.saveProcessAllBoard(boardDTO);
    }

    public QnABoardDTO findById(Integer id, String qBoardPass) {
        return boardRepositoryImpl.findByIdQnABoard(id, qBoardPass);
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
}
