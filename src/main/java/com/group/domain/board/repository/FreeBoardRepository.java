package com.group.domain.board.repository;

import com.group.domain.board.entity.FreeBoard;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface FreeBoardRepository extends JpaRepository<FreeBoard, Integer>{
    @Modifying
    @Query("update Board b set b.boardViewCount = b.boardViewCount + 1 where b.id = :id")
    Integer updateBoardViewCount(@Param("id") Integer id);

    @Query("SELECT f.id FROM FreeBoard f WHERE f.id = :id")
    Integer findBoardIdByFreeBoardId(@Param("id") Integer id);

    @Modifying
    @Transactional // 이거 나중에 영속성 걸릴듯
    @Query("update Board b set b.boardDeleteDate = CURRENT_TIMESTAMP, b.boardIsDeleted = 'Y' where b.id = :id")
    Integer updateBoardDeleted(@Param("id") Integer id);

    @Query("select f.boardId.id from FreeBoard f where f.id =:id")
    Integer findFreeBoardByBoardId(@Param("id") Integer id);
}
