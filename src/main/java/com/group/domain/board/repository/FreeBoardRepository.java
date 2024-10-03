package com.group.domain.board.repository;

import com.group.application.board.dto.FreeBoardDTO;
import com.group.domain.board.entity.FreeBoard;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FreeBoardRepository extends JpaRepository<FreeBoard, Integer>{
    @Modifying
    @Query("update Board b set b.boardViewCount = b.boardViewCount + 1 where b.id = :id")
    Integer updateBoardViewCount(@Param("id") Integer id);
}
