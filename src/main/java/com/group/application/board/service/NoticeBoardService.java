package com.group.application.board.service;

import com.group.application.board.dto.BoardDTO;
import com.group.application.board.dto.NoticeBoardDTO;
import com.group.application.board.dto.QnABoardDTO;
import com.group.domain.board.entity.Board;
import com.group.domain.board.entity.NoticeBoard;
import com.group.domain.board.repository.BoardRepositoryImpl;
import com.group.domain.board.repository.NoticeBoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class NoticeBoardService {

    private final BoardService boardService;
    private final BoardRepositoryImpl boardRepositoryImpl;
    private final NoticeBoardRepository noticeBoardRepository;

    @Autowired
    public NoticeBoardService(BoardService boardService,
                              BoardRepositoryImpl boardRepositoryImpl,
                              NoticeBoardRepository noticeBoardRepository) {
        this.boardService = boardService;
        this.boardRepositoryImpl = boardRepositoryImpl;
        this.noticeBoardRepository = noticeBoardRepository;
    }

    public void saveNoticeBoard(NoticeBoardDTO noticeBoardDTO) {
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.noticeConverterBoard(noticeBoardDTO);
        Board boardId = boardService.saveProcessAllBoard(boardDTO);
        NoticeBoard noticeEntity = getEntity(boardId);
        noticeBoardRepository.save(noticeEntity);
    }

    private NoticeBoard getEntity(Board boardId) {
        return NoticeBoard.builder()
                .boardId(boardId)
                .build();
    }

    public Page<NoticeBoardDTO> findAllByNoticeBoard(Pageable pageable) {
        return boardRepositoryImpl.findAllByNoticeBoard(pageable);
    }

    public NoticeBoardDTO findByIdNoticeBoard(Integer id) {
        noticeBoardRepository.updateBoardViewCount(id);
        return boardRepositoryImpl.findByIdNoticeBoard(id);
    }

    public NoticeBoardDTO findByIdNoticeBoardId(Integer id) {
        NoticeBoardDTO noticeBoardDTO = new NoticeBoardDTO();
        Integer noticeBoardId = noticeBoardRepository.findBoardIdByNoticeBoardId(id);
        noticeBoardDTO.setId(id);
        noticeBoardDTO.setBoardId(noticeBoardId);
        return noticeBoardDTO;
    }

    public void updateNoticeBoard(NoticeBoardDTO noticeBoardDTO) {
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.noticeConverterBoard(noticeBoardDTO);
        Board boardId = boardService.saveProcessAllBoard(boardDTO);
        NoticeBoard setEntity = getSetEntity(noticeBoardDTO, boardId);
        noticeBoardRepository.save(setEntity);
    }

    private NoticeBoard getSetEntity(NoticeBoardDTO noticeBoardDTO, Board boardId) {
        return NoticeBoard.builder()
                .id(noticeBoardDTO.getId())
                .boardId(boardId)
                .build();
    }
}
