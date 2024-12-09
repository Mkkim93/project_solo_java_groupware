package com.group.application.board.service;

import com.group.application.board.dto.CommentDTO;
import com.group.domain.board.entity.Board;
import com.group.domain.board.entity.CommentBoard;
import com.group.domain.board.repository.BoardRepository;
import com.group.domain.board.repository.CommentBoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {

    private final CommentBoardRepository commentBoardRepository;
    private final BoardRepository boardRepository;

    public Integer save(CommentDTO dto) {
        Optional<Board> optionalBoard = boardRepository.findById(dto.getBoardId());
        if (optionalBoard.isPresent()) {
            optionalBoard.get();
            CommentBoard entity = new CommentBoard(dto);
            return commentBoardRepository.save(entity).getId();
        } else {
            return null;
        }
    }

    // 게시판 번호별 댓글 목록 출력
    public Page<CommentDTO> findAll(Integer boardId, Pageable pageable) {
        Page<CommentDTO> commentDTOList = commentBoardRepository
                .findByAllComment(boardId, pageable);
        return commentDTOList;
    }

    // 비밀글 게시판 댓글을 위한 비즈니스로직 별도 구현
    public Page<CommentDTO> findAllQna(Integer boardId, Pageable pageable) {
        // TODO
        Page<CommentDTO> commentDTOList = commentBoardRepository
                .findByQnaComment(boardId, pageable);
        return commentDTOList;
    }
}
