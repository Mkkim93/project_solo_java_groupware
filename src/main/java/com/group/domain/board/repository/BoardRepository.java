package com.group.domain.board.repository;

import com.group.application.board.dto.BoardDTO;
import com.group.domain.board.entity.Board;
import org.apache.ibatis.annotations.Param;
import org.hibernate.query.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface BoardRepository extends JpaRepository<Board, Integer> , BoardRepositoryCustom, QuerydslPredicateExecutor<Board> {

    @Modifying
    @Transactional // 이거 나중에 영속성 걸릴듯
    @Query("update Board b set b.boardDeleteDate = CURRENT_TIMESTAMP, b.boardIsDeleted = 'Y' where b.id = :id")
    Integer updateBoardDeleted(@Param("id") Integer id);

    @Modifying
    @Query("update Board b set b.boardViewCount = b.boardViewCount + 1 where b.id = :id")
    Integer updateBoardViewCount(@Param("id") Integer id);

    Board getBoardById(Integer id);
}
