package com.group.domain.board.repository;

import com.group.domain.board.entity.FreeBoard;
import com.group.domain.board.entity.QnABoard;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface QnABoardRepository extends JpaRepository<QnABoard, Integer> {

    @Modifying
    @Query("update Board b set b.boardViewCount = b.boardViewCount + 1 where b.id = :id")
    Integer updateBoardViewCount(@Param("id") Integer id);

    @Query("select q.boardId.id from QnABoard q where q.id = :id and q.qBoardPass = :qBoardPass")
    Integer findQnABoardByQnAAndBoardId(@Param("id") Integer id, @Param("qBoardPass") String qBoardPass);
}
