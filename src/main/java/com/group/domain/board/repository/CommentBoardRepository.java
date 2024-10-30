package com.group.domain.board.repository;

import com.group.application.board.dto.CommentDTO;
import com.group.domain.board.entity.CommentBoard;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


public interface CommentBoardRepository extends JpaRepository<CommentBoard, Integer> {

    @Query("SELECT new com.group.application.board.dto.CommentDTO(c.id, c.comContent, e.empName, c.comRegDate, e.id, b.id) " +
            "FROM Board b, CommentBoard c, Employee e WHERE b.id = c.boardId.id and c.empId.id = e.id and b.id = :id ORDER BY c.id desc")
    Page<CommentDTO> findAllByCommentBoardId(@Param("id") Integer id, Pageable pageable);

    @Query("select new com.group.application.board.dto.CommentDTO(c.id, c.comContent, e.empName, c.comRegDate, e.id, b.id, q.boardPass)" +
            "from Employee e, Board b, CommentBoard c, QnABoard q where e.id = b.empId.id and b.id = c.boardId.id and c.boardId.id = q.boardId.id and c.boardId.id = :id and q.boardPass = :boardPass")
    Page<CommentDTO> findAllByCommentBoardIdAndPass(@Param("id") Integer id, @Param("boardPass") String boardPass, Pageable pageable);
}
