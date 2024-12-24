package com.group.application.board.service;

import com.group.application.board.dto.BoardDTO;
import com.group.application.board.dto.NoticeBoardDTO;
import com.group.domain.board.entity.Board;
import com.group.domain.board.entity.NoticeBoard;
import com.group.domain.board.repository.BoardRepositoryImpl;
import com.group.domain.board.repository.NoticeBoardRepository;
import com.group.web.mail.controller.api.MailApiController;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class NoticeBoardService {

    private final BoardReplayService boardReplayService;
    private final BoardService boardService;
    private final BoardRepositoryImpl boardRepositoryImpl;
    private final NoticeBoardRepository noticeBoardRepository;
    private final MailApiController mailApiController;

    public Page<NoticeBoardDTO> findAll(String searchKeyword, Pageable pageable) {
        return boardRepositoryImpl.findAllByNoticeBoard(searchKeyword, pageable);
    }

    public NoticeBoardDTO findByOne(Integer id) {
        NoticeBoardDTO noticeBoardDTO = boardRepositoryImpl.findByOneNoticeBoard(id);
        boardService.plusViewCount(noticeBoardDTO.getBoardId());
        return noticeBoardDTO;
    }

    public NoticeBoardDTO findByOnlyId(Integer id) {
        return boardRepositoryImpl.findByOneNoticeBoard(id);
    }

    public void save(NoticeBoardDTO dto) {
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.convertToNoticeBoardDto(dto);
        Board boardId = boardService.saveAll(boardDTO);
        noticeBoardRepository.save(setEntity(boardId));
    }

    public void update(NoticeBoardDTO noticeBoardDTO) {
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.convertToNoticeBoardDto(noticeBoardDTO);
        boardService.saveAll(boardDTO);
    }

    public void delete(Integer id) {
        boardService.delete(id);
    }

    private NoticeBoard setEntity(Board board) {
        return NoticeBoard.builder()
                .board(board)
                .build();
    }

    public Page<NoticeBoardDTO> searchKeyword(String searchKeyword, Pageable pageRequest) {
        Page<BoardDTO> boardDtoPage = boardReplayService.relaySearchKeyword(searchKeyword, pageRequest);

        Page<NoticeBoardDTO> noticeBoardDtoPage = boardDtoPage.map(boardDto -> new NoticeBoardDTO(
                boardDto.getId(),
                boardDto.getBoardId(),
                boardDto.getBoardTitle(),
                boardDto.getEmpName(),
                boardDto.getBoardContent(),
                boardDto.getBoardRegDate(),
                boardDto.getBoardViewCount(),
                boardDto.getIsDeleted()

        ));

        return noticeBoardDtoPage;
    }
}
