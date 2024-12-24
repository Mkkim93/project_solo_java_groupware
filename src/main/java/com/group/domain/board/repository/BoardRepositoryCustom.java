package com.group.domain.board.repository;

import com.group.application.board.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardRepositoryCustom {

    Page<BoardDTO> findAllByBoard(Pageable pageable);

    Page<FreeBoardDTO> findAllByFreeBoard(String searchKeyword, Pageable pageable);

    Page<NoticeBoardDTO> findAllByNoticeBoard(String searchKeyword, Pageable pageable);

    Page<FileBoardDTO> findAllByFileBoard(String searchKeyword, Pageable pageable);

    Page<QnABoardDTO> findAllByQnABoard(String searchKeyword, Pageable pageable);

    BoardDTO findByOneBoard(Integer id);

    FreeBoardDTO findByOneFreeBoard(Integer id);

    FileBoardDTO findByOneFileBoard(Integer id);

    NoticeBoardDTO findByOneNoticeBoard(Integer id);

    QnABoardDTO findByOneQnABoard(Integer id, String boardPass);

    QnABoardDTO findByOneQnABoardNotPass(Integer id);

}
