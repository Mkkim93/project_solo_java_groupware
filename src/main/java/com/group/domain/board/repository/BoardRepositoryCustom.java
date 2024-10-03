package com.group.domain.board.repository;

import com.group.application.board.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface BoardRepositoryCustom {

    Page<BoardDTO> findAllByBoard(Pageable pageable);

    Page<FreeBoardDTO> findAllByFreeBoard(Pageable pageable);

    Page<NoticeBoardDTO> findAllByNoticeBoard(Pageable pageable);

    Page<FileBoardDTO> findAllByFileBoard(Pageable pageable);

    Page<QnABoardDTO> findAllByQnABoard(Pageable pageable);

    BoardDTO findByIdBoard(Integer id);

    FreeBoardDTO findByIdFreeBoard(Integer id);

    FileBoardDTO findByIdFileBoard(Integer id);

    NoticeBoardDTO findByIdNoticeBoard(Integer id);

    QnABoardDTO findByIdQnABoard(Integer id, Integer qBoardPass);
}
