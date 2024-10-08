package com.group.application.board.service;

import com.group.application.board.dto.BoardDTO;
import com.group.application.board.dto.QnABoardDTO;
import com.group.domain.board.entity.Board;
import com.group.domain.board.entity.QnABoard;
import com.group.domain.board.repository.BoardRepositoryImpl;
import com.group.domain.board.repository.QnABoardRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
                .qBoardPass(qnABoardDTO.getQBoardPass())
                .boardId(boardId)
                .build();
    }

    public Page<QnABoardDTO> findAllByQnABoard(Pageable pageable) {
        return boardRepositoryImpl.findAllByQnABoard(pageable);
    }

    public QnABoardDTO findByIdOnly(Integer id) {
       return boardRepositoryImpl.findByIdQnABoard(id);
    }

    public void updateQnABoard(QnABoardDTO qnABoardDTO) {
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.qnaConverterBoard(qnABoardDTO);
        boardService.saveProcessAllBoard(boardDTO);
    }
}
