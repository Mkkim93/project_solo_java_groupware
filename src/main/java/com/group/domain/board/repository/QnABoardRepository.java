package com.group.domain.board.repository;

import com.group.application.board.dto.QnABoardDTO;
import com.group.domain.board.entity.FreeBoard;
import com.group.domain.board.entity.NoticeBoard;
import com.group.domain.board.entity.QnABoard;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QnABoardRepository extends JpaRepository<QnABoard, Integer> {

    @Modifying
    @Query("update Board b set b.boardViewCount = b.boardViewCount + 1 where b.id = :id")
    Integer updateBoardViewCount(@Param("id") Integer id);
}
