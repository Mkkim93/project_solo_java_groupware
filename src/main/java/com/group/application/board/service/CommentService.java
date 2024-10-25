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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CommentService {

    private final CommentBoardRepository commentBoardRepository;
    private final BoardRepository boardRepository;

    public CommentService(CommentBoardRepository commentBoardRepository, BoardRepository boardRepository) {
        this.commentBoardRepository = commentBoardRepository;
        this.boardRepository = boardRepository;
    }

    public Integer SaveCommentBoard(CommentDTO commentDTO) {
        Optional<Board> optionalBoard = boardRepository.findById(commentDTO.getBoardId());
        if (optionalBoard.isPresent()) {
            optionalBoard.get();
            CommentBoard commentBoard = new CommentBoard(commentDTO);
            return commentBoardRepository.save(commentBoard).getId();
        } else {
            return null;
        }
    }

    // 게시판 번호별 댓글 목록 출력
    public Page<CommentDTO> findAll(Integer boardId, Pageable pageable) {
        Page<CommentDTO> commentDTOList = commentBoardRepository.findAllByCommentBoardId(boardId, pageable);
        return commentDTOList;
    }
}
