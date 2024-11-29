package com.group.domain.board.repository;

import com.group.domain.board.entity.FreeBoard;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FreeBoardRepository extends JpaRepository<FreeBoard, Integer>{

    @Query("select f.id " +
            "from FreeBoard f " +
            "where f.id = :id")
    Integer findByFreeBoardId(@Param("id") Integer id);

    @Query("select f.board.id " +
            "from FreeBoard f " +
            "where f.id =:id")
    Integer findByBoardId(@Param("id") Integer id);
}
