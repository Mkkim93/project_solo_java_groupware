package com.group.application.board.service;

import com.group.application.board.dto.CommentDTO;
import com.group.domain.board.entity.Board;
import com.group.domain.board.entity.CommentBoard;
import com.group.domain.board.repository.BoardRepository;
import com.group.domain.board.repository.CommentBoardRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
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
    private final EntityManager em;

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

    public CommentDTO update(CommentDTO commentDto) {
        // 1. 기존 엔티티를 조회
        CommentBoard entity = commentBoardRepository.findById(commentDto.getId())
                .orElseThrow(() -> new EntityNotFoundException("Comment not found with id: " + commentDto.getId()));

        // 2. DTO의 정보를 엔티티에 반영
        entity.setComContent(commentDto.getComContent());  // 예시로 content만 업데이트하는 방식
        // 추가적으로 필요한 다른 필드들도 업데이트

        // 3. 변경된 엔티티를 저장
        CommentBoard updatedEntity = commentBoardRepository.save(entity);

        // 4. 엔티티를 DTO로 변환하여 반환
        return commentDto.toDto(updatedEntity);
    }

    private CommentBoard convertToComBoard(CommentDTO commentDto) {
        return CommentBoard.builder()
                .dto(commentDto)
                .build();
    }

    public void delete(Integer commentId) {
        CommentBoard commentBoard = commentBoardRepository.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException("no id"));

        commentBoard.setComIsDeleted("Y");
        commentBoardRepository.save(commentBoard);
    }
}
