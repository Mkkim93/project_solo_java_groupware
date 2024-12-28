package com.group.domain.board.repository;

import com.group.application.board.dto.NoticeBoardDTO;
import com.group.domain.board.entity.NoticeBoard;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoticeBoardRepository extends JpaRepository<NoticeBoard, Integer> {

    @Query("select new com.group.application.board.dto.NoticeBoardDTO(nb.id, b.boardTitle, b.boardRegDate) " +
            "from NoticeBoard nb, Board b where nb.board.id = b.id order by b.boardRegDate desc")
    Page<NoticeBoardDTO> mainViewNoticeBoard(Pageable pageable);
}
