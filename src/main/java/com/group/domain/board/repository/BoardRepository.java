package com.group.domain.board.repository;

import com.group.domain.board.entity.Board;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface BoardRepository extends JpaRepository<Board, Integer> , BoardRepositoryCustom, QuerydslPredicateExecutor<Board> {

    @Modifying
    @Transactional // TODO 트랜잭션 충돌 가능성 있음
    @Query("update Board b set b.boardDeleteDate = CURRENT_TIMESTAMP, b.boardIsDeleted = 'Y' where b.id = :id")
    Integer updateBoardDeleted(@Param("id") Integer id);

    @Modifying
    @Query("update Board b set b.boardViewCount = b.boardViewCount + 1 where b.id = :id")
    Integer updateBoardViewCount(@Param("id") Integer id);

    Board getBoardById(Integer id);
}
