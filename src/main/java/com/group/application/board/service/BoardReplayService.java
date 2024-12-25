package com.group.application.board.service;

import com.group.application.board.dto.BoardDTO;
import com.group.domain.board.entity.Board;
import com.group.domain.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardReplayService {

    private final BoardRepository boardRepository;

    public Page<BoardDTO> relaySearchKeyword(String searchKeyword, Pageable pageable) {
        Page<Board> result = boardRepository.findByBoardTitleContaining(searchKeyword, pageable);

        Page<BoardDTO> boardDtoPage = result.map(board -> new BoardDTO(
                board.getId(),
                board.getBoardTitle(),
                board.getBoardContent(),
                board.getEmployee().getEmpName(),
                board.getBoardRegDate(),
                board.getBoardViewCount(),
                board.getBoardIsDeleted()
        ));
        return boardDtoPage;
    }
}
